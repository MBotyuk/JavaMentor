package com.gmail.mbotyuk.springbootmvcdata.template;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class RestTemplateUserController {

	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value="/template/products", method= RequestMethod.GET)
	public String getProductsByTemplate() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value="/template/products", method= RequestMethod.POST)
	public String createProductsByTemplate(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
		return restTemplate.exchange("http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	}
	
	@RequestMapping(value="/template/products/{id}", method= RequestMethod.PUT)
	public String updateProductByTemplate(@PathVariable("id") String id, @RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
		return restTemplate.exchange("http://localhost:8080/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	@RequestMapping(value="/template/products/{id}", method= RequestMethod.DELETE)
	public String deleteProductByTemplate(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/products/"+id, HttpMethod.DELETE, entity, String.class);
		return response.getBody();
	}
}
