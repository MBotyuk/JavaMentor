package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.model.User;

import java.util.List;


public interface UserService {

    List<User> allUsers();

    boolean add(User user, String role);

    void delete(User user);

    void edit(User user, String role, Boolean flag);

    User getById(Long id);

    boolean isByEmail(String email);

    User isByName(String name);
}
