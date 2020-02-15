package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    boolean add(User user, String role);

    void setRoleByName(User user, String role);


    Optional<User> isByName(String name);
}
