package com.example.demo.Security;

import com.example.demo.Data.UserModel;
import com.example.demo.Repository.Users.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecutiryConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            UserModel user = userRepository.findByUsername(username);
            if(user != null) return user;
            throw new UsernameNotFoundException("Пользователь " + username + "не найден!");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
        throws Exception {
        return http.authorizeHttpRequests(auth-> auth
                .requestMatchers("/city/**", "/park/**", "/actuator/**").authenticated()
                .requestMatchers("/login/**", "/registration/**").anonymous()
                .requestMatchers("/", "/css/**", "/img/**").permitAll())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login"))
                .build();
    }

}
