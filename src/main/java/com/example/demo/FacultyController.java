package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacultyController {

    @GetMapping("/faculty")
    public String output(){
        return "Факультеты";
    }

}
