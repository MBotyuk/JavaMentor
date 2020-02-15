package com.gmail.mbotyuk.controller;

import com.gmail.mbotyuk.model.User;
import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private boolean error = false;
    private int id;
    private String emailOld;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


}