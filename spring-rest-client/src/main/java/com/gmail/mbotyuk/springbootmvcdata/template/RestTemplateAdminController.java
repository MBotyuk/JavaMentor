package com.gmail.mbotyuk.springbootmvcdata.template;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/rest/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestTemplateAdminController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public String getUsersByTemplate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin", HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping(value = "/{id}")
    public String getUserByTemplate(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + id, HttpMethod.GET, entity, String.class).getBody();
    }

    @PostMapping(value = "/{role}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createUserByTemplate(@RequestBody User user, @PathVariable String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + role, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping(value = "/{role}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserByTemplate(@RequestBody User user, @PathVariable String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + role, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteUserByTemplate(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/rest/admin/" + id, HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }
}