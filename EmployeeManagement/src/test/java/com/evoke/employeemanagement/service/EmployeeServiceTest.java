package com.evoke.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.VoidAnswer1;

import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepo employeeRepo;

	@InjectMocks
	private EmployeeServiceImpl empService;

	@Test
	public void  testGetEmployeeMock() {
		long empid=10;
		Mockito.when(employeeRepo.findEmployeeById(empid)).
		thenReturn(new Employee((long) (10),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03)));
		
		Employee obj=new Employee((long) (10),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
		
		assertEquals(obj.getEmpName(), empService.findEmployeeById(empid).getEmpName());
	    verify(employeeRepo,times(1)).findEmployeeById(empid);
	}
	
	@Test
	public void testFindAllEmployees() {

		Employee empOne=new Employee((long) (12),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
		Employee empTwo=new Employee((long) (13),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
	List<Employee> empList=new ArrayList<>();
		empList.add(empOne);
		empList.add(empTwo);
		Mockito.when(employeeRepo.findallTheEmployees()).
		thenReturn(empList);
		assertEquals(2, empService.getAllEmployees().size());
	    verify(employeeRepo,times(1)).findallTheEmployees();
	}
	
	@Test
	public void testAddEmployee() {
	
		Employee emp=new Employee((long) (12),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
		Mockito.when(employeeRepo.save(emp)).thenReturn(emp);
		assertEquals(emp, empService.addEmployee(emp));
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		Long empId=(long)12;
		empService.deleteEmployee(empId);

		verify(employeeRepo,times(1)).deleteById(empId);
	}	
	
}
