package com.ey.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.ey.employee.controller.EmployeeController;
import com.ey.employee.entities.Employee;
import com.ey.employee.exceptions.EmployeeNotFoundException;
import com.ey.employee.exceptions.InvalidIdException;
import com.ey.employee.service.EmployeeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Test
	void testGetAllEmployees() {
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setName("Bing");
		employee1.setEmail("bing@example.com");
		employee1.setLocation("tvm");

		Employee employee2 = new Employee();
		employee2.setId(2L);
		employee2.setName("Bong");
		employee2.setEmail("bong@example.com");
		employee2.setLocation("kochi");

		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);

		when(employeeService.getAllEmployees()).thenReturn(employees);
		List<Employee> result = employeeController.getAllEmployees();

		assertEquals("Bing", result.get(0).getName());
		assertEquals("bong@example.com", result.get(1).getEmail());
	}

	@Test
	void testGetEmployeeById() throws EmployeeNotFoundException, InvalidIdException {

		Employee employee = new Employee();
		employee.setName("Bing");
		employee.setEmail("bing@example.com");
		employee.setLocation("tvm");

		when(employeeService.getEmployeeById(1L)).thenReturn(employee);

		Employee result = employeeController.getEmployeeById(1L);
		assertEquals("Bing", result.getName());
		
	}
	
	@Test
	void testGetEmployeeByIdException() throws EmployeeNotFoundException {

//		Employee employee = new Employee();
//		employee.setName("Bing");
//		employee.setEmail("bing@example.com");
//		employee.setLocation("tvm");
//
//		Employee result = employeeController.getEmployeeById(1L);
		assertThrows(EmployeeNotFoundException.class, () -> employeeController.getEmployeeById(1L));
		
	}

	@Test
	void testCreateEmployee() {

		Employee employee = new Employee();
		employee.setName("Bing");
		employee.setEmail("bing@example.com");
		employee.setLocation("tvm");

		when(employeeService.addEmployee(employee)).thenReturn(employee);

		Employee result = employeeController.createEmployee(employee);

		assertNotNull(result);
		assertEquals("Bing", result.getName());
	}

	@Test
	void testUpdateEmployee() {

		Employee employee = new Employee();
		employee.setName("Aparna");
		employee.setEmail("aparna@example.com");
		employee.setLocation("tvm");
		when(employeeService.updateEmployee(employee)).thenReturn(employee);

		Employee result = employeeController.updateEmployee(employee);

		assertEquals(employee, result);
		assertEquals("tvm", result.getLocation());

	}

	@Test
	void testDeleteEmployee() {
		Long id = 1L;
		employeeController.deleteEmployee(id);
		verify(employeeService, times(1)).deleteEmployee(id);
	}

	@Test
	void testGetId() throws InvalidIdException{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Aparna");
		employee.setEmail("aparna@example.com");
		employee.setLocation("tvm");
		assertEquals(1L, employee.getId());
		
		Long id=null;
		assertThrows(InvalidIdException.class, () -> employeeController.getEmployeeById(id));

	}
	
	
}
