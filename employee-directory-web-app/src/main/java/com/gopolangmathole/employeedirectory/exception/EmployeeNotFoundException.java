package com.gopolangmathole.employeedirectory.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);

	}


}
