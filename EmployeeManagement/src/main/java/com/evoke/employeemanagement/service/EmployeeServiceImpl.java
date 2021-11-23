package com.evoke.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo; 
	
	@Override
	public Employee addEmployee(Employee employee) {
		employeeRepo.save(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findallTheEmployees();
	}

	@Override
	public Optional<Employee> findEmployeeById(Long id) {
		return employeeRepo.findById(id);
	}

	
	@Override
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
}

}
