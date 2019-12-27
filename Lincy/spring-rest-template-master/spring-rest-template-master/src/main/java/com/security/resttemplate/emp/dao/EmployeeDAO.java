package com.security.resttemplate.emp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.security.resttemplate.emp.domain.Employee;
import com.security.resttemplate.emp.domain.Emp;

@Repository
public class EmployeeDAO {

	// Dummy database. Initialize with some dummy values.
	private static List<Employee> employees;
	{
		employees = new ArrayList<Employee>();
		employees.add(new Employee(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
		employees.add(new Employee(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		employees.add(new Employee(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
	}
	
	public List<Emp> allemployees()
	{
		List<Emp> employees = new ArrayList<Emp>();
		Emp emp=new Emp();
		emp.setId("101");
		emp.setName("John,Doe");
		emp.setAge("21");
		emp.setEmail("djohn@gmail.com");
		emp.setPhone("121-232-3435");
		
		Emp emp1=new Emp();
		emp1.setId("102");
		emp1.setName("John,Smith");
		emp1.setAge("23");
		emp1.setEmail("Smithjohn@gmail.com");
		emp1.setPhone("121-232-3435");
		
		Emp emp2=new Emp();
		emp2.setId("103");
		emp2.setName("John,Bob");
		emp2.setAge("24");
		emp2.setEmail("Bobjohn@gmail.com");
		emp2.setPhone("121-232-3435");
		
		Emp emp3=new Emp();
		emp3.setId("104");
		emp3.setName("John,Tom");
		emp3.setAge("25");
		emp3.setEmail("Tomjohn@gmail.com");
		emp3.setPhone("121-232-3435");
		
		employees.add(emp);
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		return employees;
		//employee.add(new Emp(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		//employee.add(new Emp(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
	}

	/**
	 * Returns list of employees from dummy database.
	 * 
	 * @return list of employees
	 */
	public List list() {
		return employees;
	}

	/**
	 * Return employee object for given id from dummy database. If employee is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            employee id
	 * @return employee object for given id
	 */
	public Employee get(Long id) {

		for (Employee c : employees) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Create new employee in dummy database. Updates the id and insert new
	 * employee in list.
	 * 
	 * @param employee
	 *            employee object
	 * @return employee object with updated id
	 */
	public Employee create(Employee employee) {
		employee.setId(System.currentTimeMillis());
		employees.add(employee);
		return employee;
	}

	/**
	 * Delete the employee object from dummy database. If employee not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the employee id
	 * @return id of deleted employee object
	 */
	public Long delete(Long id) {

		for (Employee c : employees) {
			if (c.getId().equals(id)) {
				employees.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the employee object for given id in dummy database. If employee
	 * not exists, returns null
	 * 
	 * @param id
	 * @param employee
	 * @return employee object with id
	 */
	public Employee update(Long id, Employee employee) {

		for (Employee c : employees) {
			if (c.getId().equals(id)) {
				employee.setId(c.getId());
				employees.remove(c);
				employees.add(employee);
				return employee;
			}
		}

		return null;
	}

}

