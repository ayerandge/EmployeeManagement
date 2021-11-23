package com.evoke.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Employee> addEmployeeData(@RequestBody Employee empdata) {
		return new ResponseEntity(empService.addEmployee(empdata),HttpStatus.CREATED);
	}
	
	@GetMapping(value="/employee/{id}")
	public ResponseEntity<?> displayEmployeeeeById(@PathVariable Long id) {
		
		Employee empTest=empService.findEmployeeById(id);
		
		if(empTest==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		
		return ResponseEntity.ok(empTest);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> lst=empService.getAllEmployees();
		return ResponseEntity.badRequest().body(lst);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee emp) {
		Employee val= empService.updateEmployee(emp);
		return ResponseEntity.ok(val);
	}
	
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
		empService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body("Employee is removed");
	}
	
}
