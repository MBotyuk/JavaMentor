package com.gmail.mbotyuk.springbootmvcdata.template;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
public class RestTemplateUserController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value="/rest/user")
    public String getUsersByTemplate(Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        User user = (User) authentication.getPrincipal();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/user/" + ((User) authentication.getPrincipal()).getId(), HttpMethod.GET, entity, String.class).getBody();
    }
}
