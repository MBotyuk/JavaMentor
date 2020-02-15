package com.gmail.mbotyuk.security;

import com.gmail.mbotyuk.dao.UserDAO;
import com.gmail.mbotyuk.model.User;
import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.isByName(s).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
