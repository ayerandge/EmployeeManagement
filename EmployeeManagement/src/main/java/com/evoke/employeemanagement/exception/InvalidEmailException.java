package com.evoke.employeemanagement.exception;

public class InvalidEmailException extends RuntimeException{

	public InvalidEmailException(String message) {
		super(message);
	}
}
