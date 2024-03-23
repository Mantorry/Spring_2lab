package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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


    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Главная\" (Разрешен)")
    void AnonymousToMain() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Города\" (Переадресация 3xx)")
    void AnonymousToCity() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/city")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Парки\" (Переадресация 3xx)")
    void AnonymousToPark() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/park")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Авторизация\" (Разрешен)")
    void AnonymousToLogin() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Регистрация\" (Разрешен)")
    void AnonymousToRegister() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/registration")).andExpect(status().isOk());
    }

//  Пользовательский доступ
    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Главная\" (Разрешен)")
    void MockUserToMain() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Города\" (Разрешен)")
    void MockUserToCity() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/city")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Парки\" (Разрешен)")
    void MockUserToPark() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/park")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Авторизация\" (Разрешен)")
    void MockUserToLogin() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Регистрация\" (Разрешен)")
    void MockUserToRegister() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/registration")).andExpect(status().is4xxClientError());
    }
}
