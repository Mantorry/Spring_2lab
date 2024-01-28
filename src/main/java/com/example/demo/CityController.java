package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @GetMapping("/city")
    public String output(){
        return "Города";
    }
}
