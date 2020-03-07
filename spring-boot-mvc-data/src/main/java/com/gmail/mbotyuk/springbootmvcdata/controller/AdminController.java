package com.gmail.mbotyuk.springbootmvcdata.controller;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import com.gmail.mbotyuk.springbootmvcdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
            model.addAttribute("usersList", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin/edit")
    public String editUser( @RequestParam("id") Long idL,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            Model model) {
        Boolean flag = true;

            if (password == "") {
                flag = false;
                password = userService.getById(idL).getPassword();
            }

            User user = new User(idL, name, email, password);
            userService.edit(user, role, flag);
        return "redirect:/admin";
    }

    @GetMapping("/admin/add")
    public String addPage(Model model) {
        model.addAttribute("userAdd", new User());
        return "redirect:/admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("userAdd") User user, @RequestParam String role, Model model) {
            userService.add(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deletePage(Model model, @PathVariable Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        userService.delete(user);
        return "redirect:/admin";
    }
}