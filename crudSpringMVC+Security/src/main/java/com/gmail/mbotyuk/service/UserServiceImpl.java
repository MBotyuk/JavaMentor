package com.gmail.mbotyuk.service;

import com.gmail.mbotyuk.dao.UserDAO;
import com.gmail.mbotyuk.model.Role;
import com.gmail.mbotyuk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean add(User user, String role) {
        setRoleByName(user, role);
        return userDAO.add(user);
    }

    @Override
    public void setRoleByName(User user, String role) {
        if (role.equalsIgnoreCase("admin")) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
    }

    @Override
    public Optional<User> isByName(String name) {
        return (Optional<User>) userDAO.isByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}