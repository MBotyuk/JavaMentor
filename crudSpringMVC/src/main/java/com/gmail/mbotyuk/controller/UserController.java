package com.gmail.mbotyuk.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    public UserController(){}

    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        model.addAttribute("username", username);
        return "user";
    }
}