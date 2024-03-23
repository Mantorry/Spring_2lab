package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

//  Гостевой доступ
    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Главная\" (Разрешен)")
    void AnonymousToMain() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Города\" (Переадресация 3xx)")
    void AnonymousToCity() throws Exception{
        mockMvc.perform(get("/city")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Парки\" (Переадресация 3xx)")
    void AnonymousToPark() throws Exception{
        mockMvc.perform(get("/park")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Авторизация\" (Разрешен)")
    void AnonymousToLogin() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }


    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Регистрация\" (Разрешен)")
    void AnonymousToRegister() throws Exception{
        mockMvc.perform(get("/registration")).andExpect(status().isOk());
    }

//  Авторизация
    @Test
    @DisplayName(value = "Ввод корректных данных пользователя (Переадресация 3xx)")
    void correctLogin() throws Exception{
        mockMvc.perform(formLogin().user("mantorry").password("Pridaor77"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName(value = "Ввод некоректных данных пользователя (Переадресация 3xx)")
    void badCredentials() throws Exception{
        mockMvc.perform(formLogin().user("mantorryy").password("pridaor77"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

//  Пользовательский доступ
    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Главная\" (Разрешен)")
    void MockUserToMain() throws Exception{
       mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Города\" (Разрешен)")
    void MockUserToCity() throws Exception{
        mockMvc.perform(get("/city")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Парки\" (Разрешен)")
    void MockUserToPark() throws Exception{
        mockMvc.perform(get("/park")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Авторизация\" (Доступ запрещен 403)")
    void MockUserToLogin() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Регистрация\" (Доступ запрещен 403)")
    void MockUserToRegister() throws Exception{
        mockMvc.perform(get("/registration")).andExpect(status().isForbidden());
    }
}
