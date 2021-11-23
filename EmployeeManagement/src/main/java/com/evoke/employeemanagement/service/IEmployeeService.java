package com.evoke.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import com.evoke.employeemanagement.entity.Employee;

public interface IEmployeeService {

	Employee addEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee findEmployeeById(Long id);
	Employee updateEmployee(Employee employee);
	public void deleteEmployee(Long id);
	
}
