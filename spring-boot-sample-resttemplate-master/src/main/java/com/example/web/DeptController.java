package com.example.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Employee;
import com.example.repository.EmployeeDAO;

@RestController
public class DeptController {
	@Autowired
	RestTemplate restTemplate;
	
	private static final String urlPrefix="http://localhost:8080";
	
	//Using RestTemplate Exchange method 
	@GetMapping("/allEmployeesForADept")
	public  List<Employee> getAllEmployeesByDeptId(){
	RestTemplate restTemplate = new RestTemplate();
	String url=urlPrefix+"/employees";
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	
	ResponseEntity<Employee[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
	System.out.println("getEmployees-->RestTemplate Exchange method-->"+Arrays.asList(response.getBody()));	
	return Arrays.asList(response.getBody());
	}
	
	//Using RestTemplate getForObject method to retrieve an employee
	@GetMapping("/employee/{deptId}")
	public Employee getEmployee(@PathVariable Long deptId) {
		String url=urlPrefix+"/employee/101";
        Employee empResponse = restTemplate.getForObject(
        		  url,
        		  Employee.class);
        		String employeeFirstName = empResponse.getFirstName();
        System.out.println("employeeFirstName-->"+employeeFirstName);
		return empResponse;
	}
	
	//Using RestTemplate getForObject method to retrieve all employees
	@GetMapping("/employees/{deptId}")
	public List<Employee> getAllEmployee(@PathVariable Long deptId) {
		String url=urlPrefix+"/employees";
		Employee[] response = restTemplate.getForObject(
        		  url,
        		  Employee[].class);
        		
        System.out.println("all employees using getForObject-->"+Arrays.asList(response));
        return Arrays.asList(response);
	}
	
	//Using RestTemplate getForEntity method 
	@GetMapping("/allEmployeesByDeptId")
	public List<Employee> getAllEmployeesForADept() {
		String url=urlPrefix+"/employees";
		ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
		System.out.println("getEmployees-->RestTemplate-->"+Arrays.asList(response.getBody()));
		return Arrays.asList(response.getBody());
	}
	//Using RestTemplate postForEntity method //@RequestBody Employee employee
	@GetMapping("/saveEmployee")
	public Employee createEmployee() {
		String url = urlPrefix + "/createEmployee";
		
		Employee employee = new Employee();
		employee.setFirstName("Bimal");
		employee.setLastName("Bimal");
		employee.setMobile("9163797629");
		employee.setEmail("bimal@email.com");
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee, headers);
        
		ResponseEntity<Employee> response  = restTemplate.postForEntity(url, requestEntity, Employee.class);		
		return response.getBody();
	}
	
	
}
