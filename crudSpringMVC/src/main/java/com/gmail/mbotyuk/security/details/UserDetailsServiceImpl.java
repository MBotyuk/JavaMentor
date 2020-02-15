package com.gmail.mbotyuk.security.details;

import com.gmail.mbotyuk.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    final private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        UserDetails userFromDB = userDAO.isByName(name);

        if (userFromDB == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return userFromDB;
    }
}
