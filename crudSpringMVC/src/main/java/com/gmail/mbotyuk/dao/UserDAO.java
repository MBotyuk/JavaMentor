package com.gmail.mbotyuk.dao;

import com.gmail.mbotyuk.model.User;

import java.util.List;


public interface UserDAO {

    List<User> allUsers();

    boolean add(User user);

    void delete(User user);

    void edit(User user);

    User getById(Long id);

    boolean isByEmail(String email);

    User isByName(String name);
}
