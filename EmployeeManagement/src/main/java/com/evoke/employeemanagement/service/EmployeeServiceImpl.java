package com.evoke.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.exception.BusinessException;
import com.evoke.employeemanagement.exception.InvalidEmailException;
import com.evoke.employeemanagement.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		
		
		if(!isValid(employee.getEmail())) {
			
			throw new InvalidEmailException("Provide proper email");
		}
//		if(employeeRepo.findById(employee.getEmpId()).isPresent()){
//			System.out.println("here");
//			throw new BusinessException("Employee is already present");
//		}
		try {
			
		employeeRepo.save(employee);
		
		}catch (Exception e) {
			throw new BusinessException("There was some issue with saving of data to db");
		}
		return employee;
	}
	
	public static boolean isValid(String email) {
		String regex = "^[a-zA-Z]+@[a-zA-Z.-]+$";
		boolean val=email.matches(regex);
		
		return val;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee findEmployeeById(Long id) {
			Optional<Employee> emp = employeeRepo.findById(id);
			if (!(emp.isPresent()))
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
			return emp.get();
		
	}

	@Override
	public void deleteEmployee(Long id) {
		
			Optional<Employee> emp = employeeRepo.findById(id);
			if (emp == null) {
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
			}
			employeeRepo.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
			Optional<Employee> empDao = employeeRepo.findById(employee.getEmpId());
			if (empDao.isEmpty()) {
				throw new ResourceNotFoundException(
						"User with employeeId : " + employee.getEmpId() + " Does not exits");
			}
			return employeeRepo.save(employee);

		}
		
	

}
