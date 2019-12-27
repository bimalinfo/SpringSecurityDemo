package com.example.restclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Employee;

@Component
public class CustomerClient {
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${customer-service.url}")
    private String urlPrefix="http://localhost:8080";
	
	public List<Employee> getEmployees() {
		String url = urlPrefix + "/employees";
		System.out.println("urlPrefix:::::"+url);
		System.out.println("restTemplate:::::"+restTemplate);
		List<MediaType> accept = new ArrayList<>();
		accept.add(MediaType.APPLICATION_JSON_UTF8);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(accept);
        HttpEntity<Employee[]> requestEntity = new HttpEntity<Employee[]>(headers);
        //ResponseEntity<Customer[]> response = restTemplate.getForEntity(url, Customer[].class);
		ResponseEntity<Employee[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee[].class);
		return Arrays.asList(response.getBody());		
	}
	
	public Employee getEmployee(long id) {
		String url = urlPrefix + "/employee/" + id;

		List<MediaType> accept = new ArrayList<>();
		accept.add(MediaType.APPLICATION_JSON_UTF8);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(accept);
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(headers);
		
		//ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        ResponseEntity<Employee> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class);
		return response.getBody();
	}
	
	public Employee createEmployee(Employee u) {
		String url = urlPrefix + "/createEmployee";
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(u, headers);
        
		ResponseEntity<Employee> response  = restTemplate.postForEntity(url, requestEntity, Employee.class);		
		return response.getBody();
	}
	
	
}
