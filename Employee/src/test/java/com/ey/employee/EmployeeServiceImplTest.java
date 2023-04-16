package com.ey.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ey.employee.entities.Employee;
import com.ey.employee.repos.EmployeeRepository;
import com.ey.employee.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Test
	public void testGetAllEmployees() {

		List<Employee> expectedEmployees = new ArrayList<>();
		expectedEmployees.add(new Employee("Bing", "bing@gmail.com", "tvm"));
		expectedEmployees.add(new Employee("Bong", "bong@gmail.com", "kochi"));

		when(employeeRepository.findAll()).thenReturn(expectedEmployees);

		List<Employee> actualEmployees = employeeService.getAllEmployees();

		assertEquals(expectedEmployees.size(), actualEmployees.size());
		assertTrue(expectedEmployees.containsAll(actualEmployees));
	}

	@Test
	void testAddEmployee() {
		Employee employee = new Employee("Bing", "bing@gmail.com", "tvm");
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee actualEmployee = employeeService.addEmployee(employee);
		assertEquals(employee, actualEmployee);
	}

	@Test
	void testDeleteEmployee() {
		Long id = 1L;
		employeeService.deleteEmployee(id);
		verify(employeeRepository, times(1)).deleteById(id);
	}

	@Test
	void testGetEmployeeById() {
		Employee employee = new Employee();
		employee.setName("Bing");
		employee.setEmail("bing@example.com");
		employee.setLocation("tvm");
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		
		Employee result = employeeService.getEmployeeById(1L);
		
		assertEquals("Bing", result.getName());
		assertEquals("bing@example.com", result.getEmail());
	}

	@Test
	void testUpdateEmployee() {
		Employee employee = new Employee();
		employee.setName("Bing");
		employee.setEmail("bing@gmail.com");
		employee.setLocation("tvm");

		when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
			Employee emp = invocation.getArgument(0);
			emp.setId(1L);
			return emp;
		});
		Employee savedEmployee = employeeService.addEmployee(employee);

		employee.setLocation("Kochi");
		Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
		assertNotNull(updatedEmployee);

	}
	
	
}
