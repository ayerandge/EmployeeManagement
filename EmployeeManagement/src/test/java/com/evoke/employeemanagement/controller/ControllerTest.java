package com.evoke.employeemanagement.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om=new ObjectMapper();
	
	@Test
	void testAddEmployee() throws Exception{
		om.registerModule(new JavaTimeModule());
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	//	Employee emp= new Employee((long) (2),"jon13","123421213","john123@gmail.com","Admin", LocalDate.of(2020, 01, 02));
		LocalDate date=LocalDate.parse("2019-08-16");
		Employee emp= new Employee((long) (2),"jon13","123421213","john123@gmail.com","Admin", date);
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String jsonRes=om.writeValueAsString(emp);
		
		Mockito.when(empReop.save(emp)).thenReturn(emp);
		long emid=2;
	//	Mockito.when(empService.addEmployee(emp)).thenReturn(emp);
		Mockito.when(empService.findEmployeeById(emid)).thenReturn(emp);
	//	MvcResult result=mockMvc.perform(post("/employee-service/employee").content(jsonRes).
	//			contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		MvcResult result=mockMvc.perform(post("/employee-service/employee").content(jsonRes).
		contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
		
		
	//	mockMvc.perform(get("/employee-service/employee/2")).andExpect(status().isOk());
	//	verify(empService,times(1)).findEmployeeById(emid);
	}
	//@Test
	//public void testAddEmployee() throws Exception{
	//	Employee emp= new Employee((long) (2),"jon13","123421213","john123@gmail.com","Admin", LocalDate.of(2020, 01, 02));
	//	String json=super.mapToJson
	
}
