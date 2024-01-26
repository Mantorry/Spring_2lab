package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTest
{
    @Autowired
    private MockMvc mock;
    @Test
    public void func() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).
            andExpect(status().isOk()).andExpect(view().name("Main")).
            andExpect(content().string(containsString("Главная страница")));
    }
}
