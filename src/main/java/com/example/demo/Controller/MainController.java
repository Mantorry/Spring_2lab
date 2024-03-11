package com.example.demo.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/")
    public String output(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("authname", "Войти");
        if(userDetails!=null){
            model.addAttribute("authname", userDetails.getUsername()+": Выход");
        }
        return "Main";
    }
}
