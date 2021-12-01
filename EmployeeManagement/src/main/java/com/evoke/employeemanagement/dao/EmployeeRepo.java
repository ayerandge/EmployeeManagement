package com.evoke.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evoke.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
}
