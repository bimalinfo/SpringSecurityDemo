package com.security.resttemplate.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.security.resttemplate.emp.domain.Emp;
import com.security.resttemplate.emp.domain.Employee;
import com.security.resttemplate.emp.dao.EmployeeDAO;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDAO; // for demo purpose, so directly call DAO
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		System.out.println("restTemplate-->" + restTemplate);
		return employeeDAO.list();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity getEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeDAO.get(id);
		if (employee == null) {
			return new ResponseEntity("No employee found for ID " + id,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@PostMapping(value = "/createEmployee")
	public ResponseEntity createEmployee(@RequestBody Employee employee) {
		System.out.println("createEmployee-->employee--->"
				+ employee.toString());
		employeeDAO.create(employee);
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity deleteCmployee(@PathVariable Long id) {

		if (null == employeeDAO.delete(id)) {
			return new ResponseEntity("No Employee found for ID " + id,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/employee/{id}")
	public ResponseEntity updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {
		employee = employeeDAO.update(id, employee);
		if (null == employee) {
			return new ResponseEntity("No employee found for ID " + id,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(employee, HttpStatus.OK);
	}
	
	//Using empvo
	@GetMapping("/api/emps")
	public List<Emp> allEmployees() {
		return employeeDAO.allemployees();
	}

}
