package com.evoke.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoke.employeemanagement.DTO.EmployeeDTO;
import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.exception.BusinessException;
import com.evoke.employeemanagement.exception.InvalidEmailException;
import com.evoke.employeemanagement.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	@Transactional
	public Employee addEmployee(EmployeeDTO employee) {
		
		logger.info("Thread in {} and in method {}","EmployeeServiceImpl","addEmployee");
		Employee emp= mapToEntity(employee);
		if(!isValid(emp.getEmail())) {
			
			throw new InvalidEmailException("Provide proper email");
		}
//		if(employeeRepo.findById(employee.getEmpId()).isPresent()){
//			System.out.println("here");
//			throw new BusinessException("Employee is already present");
//		}
		try {
			
		employeeRepo.save(emp);
		
		}catch (Exception e) {
			throw new BusinessException("There was some issue with saving of data to db");
		}
		return emp;
	}
	
	public static boolean isValid(String email) {
		logger.info("Thread in {} and in method {} which returns value back to addEmployee","EmployeeServiceImpl","isValid");
		String regex = "^[a-zA-Z]+@[a-zA-Z.-]+$";
		boolean val=email.matches(regex);
		
		return val;
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Thread in {} and in method {}","EmployeeServiceImpl","getAllEmployees");
		return employeeRepo.findAll();
	}

	@Cacheable(cacheNames = "employeeCache",key = "#id")
	@Override
	public Employee findEmployeeById(Long id) {
		logger.info("Thread in {} and in method {}","EmployeeServiceImpl","findEmployeeById");
			Optional<Employee> emp = employeeRepo.findById(id);
			if (!(emp.isPresent())) {
				logger.error("Service layer {} and method {} : Resource not found with id :"
						+ " {} ","EmployeeServiceImpl","findEmployeeById",id);
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
				}
			return emp.get();
	
	}

	@CacheEvict(cacheNames = "employeeCache", key ="#id" )
	@Override
	public void deleteEmployee(Long id) {
		logger.info("Thread in {} and in method {}","EmployeeServiceImpl","deleteEmployee");
			Optional<Employee> emp = employeeRepo.findById(id);
			if (emp == null) {
				logger.error("Service layer {} and method {} : Resource not found with id :"
						+ " {} ","EmployeeServiceImpl","deleteEmployeeId",id);
				throw new ResourceNotFoundException("Resource not found for Id : " + id);
			}
			employeeRepo.deleteById(id);
	}

	@CachePut(value = "employeeCache",key = "#employee.empId")
	@Override
	public Employee updateEmployee(EmployeeDTO employee) {
		logger.info("Thread in {} and in method {}","EmployeeServiceImpl","updateEmployee");
			Employee emp=mapToEntity(employee);
			Optional<Employee> empDao = employeeRepo.findById(emp.getEmpId());
			if (empDao.isEmpty()) {
				logger.error("Service layer {} and method {} : Resource not found with id :"
						+ " {} ","EmployeeServiceImpl","updateEmployee",emp.getEmpId());
				throw new ResourceNotFoundException(
						"User with employeeId : " + employee.getEmpId() + " Does not exits");
			}
			return employeeRepo.save(emp);

		}
		
	public Employee mapToEntity(EmployeeDTO emp) {
		return new Employee(emp.getEmpId(), emp.getEmpName(), emp.getEmpPhone(), emp.getEmail(), emp.getCreatedBy(), emp.getCreatedOn());
	}
	

}
