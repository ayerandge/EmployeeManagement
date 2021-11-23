package com.evoke.employeemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evoke.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	@Query(value="select *  from employee e",nativeQuery = true)
	List<Employee> findallTheEmployees();

	@Query(value="select e from Employee e where e.id= :id")
	Employee findEmployeeById(Long id);

}
