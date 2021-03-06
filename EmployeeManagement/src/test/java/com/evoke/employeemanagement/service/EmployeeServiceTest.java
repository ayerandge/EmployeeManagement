package com.evoke.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.evoke.employeemanagement.DTO.EmployeeDTO;
import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.exception.InvalidEmailException;
import com.evoke.employeemanagement.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepo employeeRepo;

	@InjectMocks
	private EmployeeServiceImpl empService;

	@Test
	public void testGetEmployeeMock() {
		long empid = 10;
		Mockito.when(employeeRepo.findById(empid)).thenReturn(Optional.of(new Employee((long) (10), "John", "123421213", "john@gmail.com", "Admin", LocalDate.of(2020, 02, 03)))
				);
		Employee obj = new Employee((long) (10), "John", "123421213", "john@gmail.com", "Admin",
				LocalDate.of(2020, 02, 03));
		assertEquals(obj.getEmpName(), empService.findEmployeeById(empid).getEmpName());
		verify(employeeRepo, times(1)).findById(empid);
	}

	@Test
	public void testFindAllEmployees() {

		Employee empOne = new Employee((long) (12), "John", "123421213", "john@gmail.com", "Admin",
				LocalDate.of(2020, 02, 03));
		Employee empTwo = new Employee((long) (13), "John", "123421213", "john@gmail.com", "Admin",
				LocalDate.of(2020, 02, 03));
		List<Employee> empList = new ArrayList<>();
		empList.add(empOne);
		empList.add(empTwo);
		Mockito.when(employeeRepo.findAll()).thenReturn(empList);
		assertEquals(2, empService.getAllEmployees().size());
		verify(employeeRepo, times(1)).findAll();
	}

	@Test
	public void testAddEmployee() {
		EmployeeDTO empD = new EmployeeDTO((long) (12), "John", "123421213", "john@gmail.com", "Admin",
				LocalDate.of(2020, 02, 03));
		Employee emp=mapToEntity(empD);
		Mockito.when(employeeRepo.save(Mockito.any())).thenReturn(emp);
		assertEquals(emp.getEmpName(), empService.addEmployee(empD).getEmpName());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Employee emp = new Employee((long) (12), "John", "123421213", "john@gmail.com", "Admin",
				LocalDate.of(2020, 02, 03));
		Long empId = (long) 15;
		when(employeeRepo.findById(empId)).thenReturn(Optional.of(emp));
		empService.deleteEmployee(empId);
		verify(employeeRepo, times(1)).deleteById(empId);
	}

	@Test
	public void testUpdateUser() throws Exception {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(12L);
		emp.setEmpName("New Name");
		emp.setEmail("email@email.com");
		emp.setEmpPhone("123213424");
		emp.setCreatedBy("Admin");
		emp.setCreatedOn(LocalDate.now());
		Employee employee=mapToEntity(emp);
		when(employeeRepo.findById(emp.getEmpId())).thenReturn(Optional.of(employee));
		//when(employeeRepo.save(entity))
		empService.updateEmployee(emp);
		//verify(employeeRepo).save(employee);
		verify(employeeRepo).findById(emp.getEmpId());
	}

	@Test()
	public void testResourceNotfoundException() {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(12L);
		emp.setEmpName("New Name");
		emp.setEmail("email@email.com");
		emp.setEmpPhone("123213424");
		emp.setCreatedBy("Admin");
		emp.setCreatedOn(LocalDate.now());
		when(employeeRepo.findById(emp.getEmpId()))
				.thenThrow(new ResourceNotFoundException("No Employee found"));
		Assertions.assertThrows(ResourceNotFoundException.class, () -> empService.updateEmployee(emp));
		verify(employeeRepo).findById(emp.getEmpId());
	//	verify(employeeRepo, times(0)).save(emp);
	}

	@Test()
	public void testInvalidException() {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(20L);
		emp.setEmpName("New Name");
		emp.setEmail("email123@email.com");
		emp.setEmpPhone("123213424");
		emp.setCreatedBy("Admin");
		emp.setCreatedOn(LocalDate.now());
		Assertions.assertThrows(InvalidEmailException.class, () -> empService.addEmployee(emp));
	}
	
	public Employee mapToEntity(EmployeeDTO emp) {
		return new Employee(emp.getEmpId(), emp.getEmpName(), emp.getEmpPhone(), emp.getEmail(), emp.getCreatedBy(), emp.getCreatedOn());
	}
	
}
