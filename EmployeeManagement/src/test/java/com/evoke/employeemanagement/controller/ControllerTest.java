package com.evoke.employeemanagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.evoke.employeemanagement.dao.EmployeeRepo;
import com.evoke.employeemanagement.entity.Employee;
import com.evoke.employeemanagement.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers=EmployeeController.class)
public class ControllerTest {

	@MockBean
	EmployeeServiceImpl empService;
	
	@MockBean
	EmployeeRepo empReop;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	
	@BeforeAll
	public void setUp() {
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	ObjectMapper om=new ObjectMapper();
	
	@Test
	void testAddEmployee() throws Exception{
		Employee emp= new Employee((long) (13),"jon13","123421213","john123@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		
		String jsonRes=om.writeValueAsString(emp);
		
		Mockito.when(empReop.save(emp)).thenReturn(emp);
		
		Mockito.when(empService.addEmployee(emp)).thenReturn(emp);
		
		MvcResult result=mockMvc.perform(post("/employee-service/employee").content(jsonRes).
				contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
	}
}
