package com.gmail.mbotyuk.springbootmvcdata.controller;

import com.gmail.mbotyuk.springbootmvcdata.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

@Autowired
UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        model.addAttribute("user", userServiceImpl.isByName(username));
        return "user";
    }
}