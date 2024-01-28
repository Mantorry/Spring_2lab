package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkController {
    @GetMapping("/park")
    public String output(){
        return "Парки";
    }
}
