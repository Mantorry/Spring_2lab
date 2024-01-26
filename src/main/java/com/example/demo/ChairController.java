package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChairController {

    @GetMapping("/chair")
    public String output(){
        return "Кафедры";
    }
}
