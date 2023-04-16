package com.ey.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.employee.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
