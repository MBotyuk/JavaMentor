package com.gmail.mbotyuk.springbootmvcdata.service;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import com.gmail.mbotyuk.springbootmvcdata.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class UserServiceImpl {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) { //@Qualifier("encoder")
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public User isByName(@PathVariable Long id) {
        return userDAO.findById(id).get();
    }


}