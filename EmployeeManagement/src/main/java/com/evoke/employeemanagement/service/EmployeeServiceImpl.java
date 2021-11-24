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
		return employeeRepo.findallTheEmployees();
	}

	@Override
	public Employee findEmployeeById(Long id) {
			Employee emp = employeeRepo.findEmployeeById(id);
			if (emp == null || emp.getEmpName().isEmpty())
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
			return emp;
		
	}

	@Override
	public void deleteEmployee(Long id) {
		
			Employee emp = employeeRepo.findEmployeeById(id);
			System.out.println("Hello");
			if (emp == null) {
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
			}
			employeeRepo.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
			Employee empDao = employeeRepo.findEmployeeById(employee.getEmpId());
			if (empDao == null) {
				throw new ResourceNotFoundException(
						"User with employeeId : " + employee.getEmpId() + " Does not exits");
			}
			return employeeRepo.save(employee);

		}
		
	

}
