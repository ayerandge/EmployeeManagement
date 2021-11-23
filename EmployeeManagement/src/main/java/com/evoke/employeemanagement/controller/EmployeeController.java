package com.evoke.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.service.EmployeeServiceImpl;
import com.evoke.employeemanagement.service.IEmployeeService;

@RestController
@RequestMapping(value="/employee-service")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService empService;
	
	@PostMapping(value="/employee")
	public Employee addEmployeeData(@RequestBody Employee empdata) {
		return empService.addEmployee(empdata);
	}
	
	@GetMapping(value="/employee/{id}")
	public Optional<Employee> displayEmployeeeeById(@PathVariable Long id) {
		return empService.findEmployeeById(id);
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		List<Employee> lst=empService.getAllEmployees();
		return lst;
	}
	
	@PutMapping("/employee")
	public Employee updateEmployeeById(@RequestBody Employee emp) {
		return empService.updateEmployee(emp);
	}
	
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		empService.deleteEmployee(id);
	}
	
}
