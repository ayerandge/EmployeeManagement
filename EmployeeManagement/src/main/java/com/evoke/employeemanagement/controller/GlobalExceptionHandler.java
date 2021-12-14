package com.evoke.employeemanagement.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.evoke.employeemanagement.exception.ApiResponseException;
import com.evoke.employeemanagement.exception.BusinessException;
import com.evoke.employeemanagement.exception.InvalidEmailException;
import com.evoke.employeemanagement.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseException> handleResourceNotFoundException(ResourceNotFoundException ex){
		ApiResponseException response=new ApiResponseException();
		response.setErrorMessage(ex.getMessage());
		response.setErrorCode(HttpStatus.NOT_FOUND);
		response.setTimeStamp(LocalDateTime.now());
		response.setDetailedMessage(ex.getStackTrace()[0]);
		return new ResponseEntity<ApiResponseException>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponseException> handleBusinessException(BusinessException ex){
		ApiResponseException response=new ApiResponseException();
		response.setErrorMessage(ex.getMessage());
		response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTimeStamp(LocalDateTime.now());
		response.setDetailedMessage(ex.getStackTrace()[0]);
		return new ResponseEntity<ApiResponseException>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ApiResponseException> handleInvalidException(InvalidEmailException ex){
		ApiResponseException response=new ApiResponseException();
		response.setErrorMessage(ex.getMessage());
		response.setErrorCode(HttpStatus.BAD_REQUEST);
		response.setTimeStamp(LocalDateTime.now());
		response.setDetailedMessage(ex.getStackTrace()[0]);
		return new ResponseEntity<ApiResponseException>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponseException> handleIllegalArgumentException(IllegalArgumentException ex){
		ApiResponseException response=new ApiResponseException();
		response.setErrorMessage("Invalid parameter");
		response.setErrorCode(HttpStatus.NOT_ACCEPTABLE);
		response.setTimeStamp(LocalDateTime.now());
		response.setDetailedMessage(ex.getStackTrace()[0]);
		return new ResponseEntity<ApiResponseException>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseException> handleOtherException(Exception ex){
		ApiResponseException response=new ApiResponseException();
		response.setErrorMessage("Unknown error"+ex.getMessage());
		response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTimeStamp(LocalDateTime.now());
		response.setDetailedMessage(ex.getStackTrace()[0]);
		return new ResponseEntity<ApiResponseException>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
