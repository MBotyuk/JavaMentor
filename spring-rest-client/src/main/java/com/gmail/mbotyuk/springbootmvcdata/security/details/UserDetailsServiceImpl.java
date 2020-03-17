package com.gmail.mbotyuk.springbootmvcdata.security.details;

import com.gmail.mbotyuk.springbootmvcdata.models.Role;
import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;


@RestController
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        User userFromDB = null;
        userFromDB = restTemplate.exchange("http://localhost:8081/rest/auth/{name}", HttpMethod.GET, entity, User.class, name).getBody();
        if (userFromDB == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        if (userFromDB.getStrRole().equalsIgnoreCase("ROLE_ADMIN")) {
            userFromDB.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            userFromDB.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }

        return userFromDB;
    }
}
