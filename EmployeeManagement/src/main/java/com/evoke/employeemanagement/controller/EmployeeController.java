package com.evoke.employeemanagement.controller;

import java.util.List;

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

import com.evoke.employeemanagement.DTO.EmployeeDTO;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.service.IEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value="/employee-service")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService empService;
	
	@Operation(summary = "Add Employee",description = "Add an employee to the DB", tags="Post")
	@ApiResponses({@ApiResponse(responseCode = "201",content = @Content(mediaType ="application/json" )),
	@ApiResponse(responseCode = "500",content = @Content(mediaType ="application/json" ))})
	@PostMapping(value="/employee")
	public ResponseEntity<Employee> addEmployeeData(@RequestBody EmployeeDTO empdata) {
		Employee emp=empService.addEmployee(empdata);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
	
	@Operation(summary = "Fetch single employee",description = "fetch one employee using employee Id", tags= {"Get","Post"})
	@ApiResponses(@ApiResponse(responseCode = "200",content = @Content(mediaType ="application/json" )))
	@GetMapping(value="/employee/{id}")
	public ResponseEntity<?> displayEmployeeeeById(@PathVariable Long id) {
		
		Employee empTest=empService.findEmployeeById(id);
		return ResponseEntity.ok(empTest);
	}
	
	@Operation(summary = "Fetch all the employees",description = "fetch all the employees", tags="Get")
	@ApiResponses(@ApiResponse(responseCode = "200",content = @Content(mediaType ="application/json" )))
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> lst=empService.getAllEmployees();
		return ResponseEntity.ok().body(lst);
	}
	@Operation(summary = "Update an existing employee",description = "Update the details of an employee based on employee Id",
			tags="Put")
	@ApiResponses(@ApiResponse(responseCode = "200",content = @Content(mediaType ="application/json" )))
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody EmployeeDTO emp) {
		Employee val= empService.updateEmployee(emp);
		return ResponseEntity.ok(val);
	}
	
	@Operation(summary = "Delete Employee",description = "Delete an employee from data base based on Id", tags="Delete")
	@ApiResponses(@ApiResponse(responseCode = "200",content = @Content(mediaType ="application/json" )))
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
		empService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body("Employee is removed");
	}
	
}
