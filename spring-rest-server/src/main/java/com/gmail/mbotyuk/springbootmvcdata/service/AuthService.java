package com.gmail.mbotyuk.springbootmvcdata.service;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import com.gmail.mbotyuk.springbootmvcdata.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rest/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class AuthService {

    private final UserDAO userDAO;

    @Autowired
    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{name}")
    public User getById(@PathVariable String name) {
        User user = userDAO.findByName(name);
        return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles().iterator().next().getNameRole());
    }
}
