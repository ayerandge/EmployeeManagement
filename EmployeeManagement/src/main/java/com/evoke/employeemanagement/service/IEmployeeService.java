package com.evoke.employeemanagement.service;

import java.util.List;

import com.evoke.employeemanagement.DTO.EmployeeDTO;
import com.evoke.employeemanagement.entity.Employee;

public interface IEmployeeService {

	Employee addEmployee(EmployeeDTO employee);
	List<Employee> getAllEmployees();
	Employee findEmployeeById(Long id);
	Employee updateEmployee(EmployeeDTO employee);
	public void deleteEmployee(Long id) ;
	
}
