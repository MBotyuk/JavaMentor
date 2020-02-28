package com.gmail.mbotyuk.springbootmvcdata.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    public LoginController(){}

    @GetMapping("/login")
    public String getLoginPage(Model model, Authentication authentication) {

        String url = "login";
        if (authentication != null){
            if ( authentication.getAuthorities().iterator().next().getAuthority().contains("ADMIN")){
                url = "redirect:/admin";
            } else {
                url = "redirect:/user";
            }
        }

        return url;
    }

//    @PostMapping("/login?error")
//    public String postLoginPage(Model model , Authentication authentication) {
//        model.addAttribute("loginError", true);
//
//        return "login";
//    }
}
