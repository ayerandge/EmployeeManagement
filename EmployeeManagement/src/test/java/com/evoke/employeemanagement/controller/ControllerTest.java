package com.evoke.employeemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@WebMvcTest(controllers=EmployeeController.class)
public class ControllerTest {

	@MockBean
	EmployeeServiceImpl empService;
	
	@MockBean
	EmployeeRepo empReop;
	
	@Mock
	Employee employee;
	
	
	private static MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	static ObjectMapper om=new ObjectMapper();
	
	List<Employee> empList;
	@BeforeEach
	public void setUp() {
		om.registerModule(new JavaTimeModule());
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	void testAddEmployee() throws Exception{
		String uri="/employee-service/employee";
		Employee emp= new Employee((long) (2),"jon13","123421213","john@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		String jsonRes=om.writeValueAsString(emp);
		when(empService.addEmployee(Mockito.any(Employee.class))).thenReturn(emp);
			MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRes)).andReturn();
		assertEquals(201, result.getResponse().getStatus());
	}
	
	@Test
	void testGetEmployeeById() throws Exception {
		Employee emp= new Employee((long) (2),"jon13","123421213","john123@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		long emid=2;
		Mockito.when(empService.findEmployeeById(emid)).thenReturn(emp);
		MvcResult result=mockMvc.perform(get("/employee-service/employee/2")).andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	void testGetEmployees() throws Exception {
		Employee empOne=new Employee((long) (12),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
		Employee empTwo=new Employee((long) (13),"John","123421213","john@gmail.com","Admin", LocalDate.of(2020, 02, 03));
	    List<Employee> empList=new ArrayList<>();
		empList.add(empOne);
		empList.add(empTwo);
		Mockito.when(empService.getAllEmployees()).thenReturn(empList);
		MvcResult result=mockMvc.perform(get("/employee-service/employee/")).andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		//System.out.println(result.getResponse().getContentAsString());
		assertEquals(result.getResponse().getContentAsString(),om.writeValueAsString(empList));
	}
	
	@Test
	void testDeleteEmployee() throws Exception{
		Long empId=(long)12;
		doNothing().when(empService).deleteEmployee(empId);;
		MvcResult result=mockMvc.perform(delete("/employee-service/employee/12")).andExpect(status().isOk()).andReturn();
		assertEquals(200,result.getResponse().getStatus());
		verify(empService, times(1)).deleteEmployee(empId);
		
		
	}
	@Test
	void testUpdateEmployee() throws Exception{
		String uri="/employee-service/employee";
		Employee emp= new Employee((long) (2),"jon13","123421213","john@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		String jsonRes=om.writeValueAsString(emp);
		when(empService.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);
			MvcResult result=mockMvc.perform(MockMvcRequestBuilders.put(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRes)).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
}

