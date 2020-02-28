package com.gmail.mbotyuk.springbootmvcdata.controller;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import com.gmail.mbotyuk.springbootmvcdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/admin/")
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

//    @GetMapping("/admin/edit/{id}")
//    public String editPage(@PathVariable("id") Long id, Model model) {
////        idEdit = id;
//        userOld = userService.getById(id);
////        if (userOld.getAuthorities().iterator().next().getAuthority().contains("ADMIN")){
//
//        if (userOld.getRoles().toString().contains("ADMIN")){
//
//            model.addAttribute("flag", "admin");
//        } else {
//            model.addAttribute("flag", "user");
//        }
//        if (error) {
//            model.addAttribute("error", "данный email уже используется");
//            error = false;
//        }
//        model.addAttribute("userEdit", userOld);
//        return "redirect:/admin";
//    }

    @PostMapping("/admin/edit")
    public String editUser( @RequestParam("id") Long idL,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            Model model) {
        Boolean flag = true;
        if (userService.getById(idL).getEmail().equals(email) || userService.isByEmail(email)) {

            if (password == "") {
                flag = false;
                password = userService.getById(idL).getPassword();
            }

            User user = new User(idL, name, email, password);
            userService.edit(user, role, flag);
        } else {
            error = true;
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/add")
    public String addPage(Model model) {
        model.addAttribute("userAdd", new User());

        if (error) {
            model.addAttribute("error", "Email error");
            error = false;
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("userAdd") User user, @RequestParam String role, Model model) {

        if (userService.isByEmail(user.getEmail())) {
            userService.add(user, role);
        } else {
            error = true;
        }
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