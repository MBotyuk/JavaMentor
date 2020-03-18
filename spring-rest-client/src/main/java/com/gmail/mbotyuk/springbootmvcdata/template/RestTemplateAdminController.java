package com.gmail.mbotyuk.springbootmvcdata.template;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping(value = "/rest/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestTemplateAdminController {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Autowired
    public RestTemplateAdminController(RestTemplate restTemplateAdmin, HttpHeaders headersAdmin) {
        this.restTemplate = restTemplateAdmin;
        this.headers = headersAdmin;
    }

    @GetMapping
    public String getUsersByTemplate() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin", HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping(value = "/{id}")
    public String getUserByTemplate(@PathVariable Long id) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + id, HttpMethod.GET, entity, String.class).getBody();
    }

    @PostMapping(value = "/{role}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createUserByTemplate(@RequestBody User user, @PathVariable String role) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + role, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping(value = "/{role}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserByTemplate(@RequestBody User user, @PathVariable String role) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange("http://localhost:8081/rest/admin/" + role, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteUserByTemplate(@PathVariable Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/rest/admin/" + id, HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }
}