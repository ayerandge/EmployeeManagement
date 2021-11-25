package com.evoke.employeemanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long empId;
	@Column(name = "uname", nullable = false)
	private String empName;
	@Column(name = "phone", nullable = false)
	private String empPhone;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "create_by", nullable = false)
	private String createdBy;
	
	@JsonFormat(pattern="yyyy-MM-dd",shape = Shape.STRING)
	@Column(name = "create_on", nullable = false)
	private LocalDate createdOn;
	
	public Employee() {
		
	}
	
	public Employee(Long empId, String empName, String empPhone, String email, String createdBy, LocalDate createdOn) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPhone = empPhone;
		this.email = email;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

}
