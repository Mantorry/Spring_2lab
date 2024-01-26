package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @GetMapping("/teacher")
    public String output(){
        return "Преподаватели";
    }
}
