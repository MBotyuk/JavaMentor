package com.gmail.mbotyuk.controller;

import com.gmail.mbotyuk.model.User;
import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
public class AdminController {

    private boolean error = false;
    private Long idEdit;
    private String emailOld;

    final private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


}
