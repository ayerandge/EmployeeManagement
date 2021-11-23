package com.evoke.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

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
		verify(employeeRepo.findEmployeeById(empid));
	}

}
