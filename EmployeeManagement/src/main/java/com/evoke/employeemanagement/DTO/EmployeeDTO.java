package com.evoke.employeemanagement.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Component
public class EmployeeDTO {
		
		private Long empId;
		
		private String empName;
		
		private String empPhone;
		
		private String email;
		
		private String createdBy;
		
		@JsonFormat(pattern="yyyy-MM-dd",shape = Shape.STRING)
		private LocalDate createdOn;
		
		public EmployeeDTO() {
			
		}
		
		public EmployeeDTO(Long empId, String empName, String empPhone, String email, String createdBy, LocalDate createdOn) {
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
