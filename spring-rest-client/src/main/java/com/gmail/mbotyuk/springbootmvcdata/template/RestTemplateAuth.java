package com.gmail.mbotyuk.springbootmvcdata.template;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateAuth {

    private static RestTemplate restTemplate;
    private static HttpHeaders headers;

    @Autowired
    public RestTemplateAuth(RestTemplate restTemplate, HttpHeaders headers){
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    public static UserDetails getUserByUsername(String name) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/auth/{name}", HttpMethod.GET, entity, User.class, name).getBody();
    }
}
