package com.gmail.mbotyuk.controller;

import com.gmail.mbotyuk.model.User;
import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    private boolean error = false;
    User userOld;

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
//        idEdit = id;
        userOld = userService.getById(id);
        if (userOld.getAuthorities().iterator().next().getAuthority().contains("ADMIN")){
            model.addAttribute("roleOne", "ADMIN");
            model.addAttribute("roleTwo", "USER");
        } else {
            model.addAttribute("roleOne", "USER");
            model.addAttribute("roleTwo", "ADMIN");
        }
        if (error) {
            model.addAttribute("error", "данный email уже используется");
            error = false;
        }
        model.addAttribute("user", userOld);
        return "editPage";
    }

    @PostMapping("/admin/edit")
    public String editUser(
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            Model model) {
        String url;
        Boolean flag = true;
        if (userOld.getEmail().equals(email) || userService.isByEmail(email)) {
            url = "redirect:/admin";

            if (password == "") {
                flag = false;
                password = userOld.getPassword();
            }

            User user = new User(userOld.getId(), name, email, password);
            userService.edit(user, role, flag);
        } else {
            error = true;
            url = "/admin/edit/" + userOld.getId();
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
    public String addUser(@ModelAttribute("user") User user, @RequestParam String role, Model model) {
        String url;
        if (userService.isByEmail(user.getEmail())) {
            url = "redirect:/admin";
            userService.add(user, role);
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