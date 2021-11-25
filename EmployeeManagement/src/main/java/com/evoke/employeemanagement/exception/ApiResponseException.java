package com.evoke.employeemanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ApiResponseException {

	
	private String errorMessage;
	
	private HttpStatus errorCode;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	private StackTraceElement detailedMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public StackTraceElement getDetailedMessage() {
		return detailedMessage;
	}

	public void setDetailedMessage(StackTraceElement detailedMessage) {
		this.detailedMessage = detailedMessage;
	}
	
	
	
	
}
