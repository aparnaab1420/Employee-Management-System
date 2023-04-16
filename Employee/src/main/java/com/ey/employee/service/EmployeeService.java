package com.ey.employee.service;

import java.util.List;

import com.ey.employee.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Long id);
	
	public Employee addEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employee);
	
	public void deleteEmployee(Long id);
}
