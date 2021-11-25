package com.evoke.employeemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om=new ObjectMapper();
	
	@Test
	void testAddEmployee() throws Exception{
		String uri="/employee-service/employee";
		om.registerModule(new JavaTimeModule());
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
		Employee emp= new Employee((long) (2),"jon13","123421213","john@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String jsonRes=om.writeValueAsString(emp);
		when(empService.addEmployee(Mockito.any(Employee.class))).thenReturn(emp);
		System.out.println("im here");
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRes)).andReturn();
		assertEquals(201, result.getResponse().getStatus());
		//assertEquals(result.getResponse().getContentAsString(), om.writeValueAsString(employee));
		//MvcResult result=mockMvc.perform(post("/employee-service/employee").content(jsonRes).
		//contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
		System.out.println("Then here");
		System.out.println(jsonRes);
		//verify(empService,times(1)).addEmployee(emp);
	}
	
	@Test
	void testGetEmployeeById() throws Exception {
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
		Employee emp= new Employee((long) (2),"jon13","123421213","john123@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		long emid=2;
		Mockito.when(empService.findEmployeeById(emid)).thenReturn(emp);
		MvcResult result=mockMvc.perform(get("/employee-service/employee/2")).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
