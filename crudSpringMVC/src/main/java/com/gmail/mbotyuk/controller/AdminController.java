package com.gmail.mbotyuk.controller;

import com.gmail.mbotyuk.model.Role;
import com.gmail.mbotyuk.model.User;
import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AdminController {

    private boolean error = false;
    private Long idEdit;
    private String emailOld;
    Set<Role> roleOld;

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

    @GetMapping("/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        this.idEdit = id;
        User user = userService.getById(idEdit);
        emailOld = user.getEmail();
        roleOld = user.getRoles();
        if (error) {
            model.addAttribute("error", "данный email уже используется");
            error = false;
        }
        model.addAttribute("user", user);
        return "editPage";
    }

    @PostMapping("/admin/edit")
    public String editUser(
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        String url;
        if (emailOld.equals(email) || userService.isByEmail(email)) {
            url = "redirect:/admin";
            User user = new User(idEdit, name, email, password, roleOld);
            userService.edit(user);
        } else {
            error = true;
            url = "/admin/edit/" + idEdit;
        }
        return url;
    }

    @GetMapping("/admin/add")
    public String addPage(Model model) {
        if (error) {
            model.addAttribute("error", "данный email уже используется");
            error = false;
        }
        return "addPage";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        String url;
        if (userService.isByEmail(user.getEmail())) {
            url = "redirect:/admin";
            userService.add(user, "USER");
        } else {
            error = true;
            url = "addPage";
        }
        return url;
    }

    @GetMapping("/admin/delete/{id}")
    public String deletePage(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        userService.delete(user);
        return "delPage";
    }

    @PostMapping("/admin/delete")
    public String deleteUser() {
        return "redirect:/admin";
    }
}