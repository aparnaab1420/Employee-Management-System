package com.ey.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.employee.entities.Employee;
import com.ey.employee.exceptions.EmployeeNotFoundException;
import com.ey.employee.exceptions.InvalidIdException;
import com.ey.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {

		return employeeService.getAllEmployees();

	}

	@GetMapping("/getEmployee")
	public Employee getEmployeeById(@RequestParam(name = "id", required = false) Long id) throws EmployeeNotFoundException, InvalidIdException {
		if (id == null || id.equals("")) {
			throw new InvalidIdException("Employee ID is required");
		}

		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee not found with the given id!");

		}
		return employee;

	}

	@PostMapping("/addEmployee")
	public Employee createEmployee(@RequestBody Employee employee) {

		return employeeService.addEmployee(employee);

	}

	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployee(@RequestBody Employee employee) {

		return employeeService.updateEmployee(employee);

	}

	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

	}
}
