package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.dao.UserDAO;
import com.gmail.mbotyuk.model.Role;
import com.gmail.mbotyuk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) { //@Qualifier("encoder")
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getById(Long id) {
        return (User) userDAO.getById(id);
    }

    @Override
    public boolean isByEmail(String email) {
        return userDAO.isByEmail(email);
    }

    @Override
    public User isByName(String name) {
        return userDAO.isByName(name);
    }

    @Override
    public boolean add(User user, String role) {
        User userFromDB = isByName(user.getName());

        if (userFromDB != null) {
            return false;
        }

        if (role.equalsIgnoreCase("admin")) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDAO.add(user);
    }

    @Override
    public void edit(User user, String role, Boolean flag) {
        if (role.equalsIgnoreCase("admin")) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        if (flag){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDAO.edit(user);
    }
}