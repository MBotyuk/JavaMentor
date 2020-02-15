package com.gmail.mbotyuk.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/")
    public String getProfilePage(Authentication authentication, ModelMap model){

        return "index";
    }
}
