package com.gopolangmathole.employeedirectory.advicer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gopolangmathole.employeedirectory.exception.EmployeeNotFoundException;
import com.gopolangmathole.employeedirectory.entity.EmployeeResponse;
import com.gopolangmathole.employeedirectory.entity.ExceptionReport;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.service.ExceptionService;

import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeControllerAdviser {

	// auto wiring dependencies
	@Autowired
	private ExceptionService exceptionService;

	private ExceptionReport exceptionReport;
	
	private GetCurrentDateAndTime getCurrentDateAndTime;

	/*
	 * Creating controller adviser for All required exceptions
	 * 
	 */

	@ExceptionHandler
	public ResponseEntity<EmployeeResponse> sqlException(SQLException  sqlException) {
		EmployeeResponse employee = new EmployeeResponse();
		exceptionReport = new ExceptionReport();
		getCurrentDateAndTime = new GetCurrentDateAndTime();

		employee.setStatusCode((int) HttpStatus.INTERNAL_SERVER_ERROR.value());
		employee.setMessage(sqlException.getMessage());
		employee.setDateTime(getCurrentDateAndTime.getCurrentFullDate());

		// setting the error response and updating database
		exceptionReport.setCode((int) employee.getStatusCode());
		exceptionReport.setException(employee.getMessage().toString());
		exceptionReport.setTime(employee.getDateTime());

		// saving all the exceptions to the database (using JPA repository).
		exceptionService.save(exceptionReport);

		return new ResponseEntity<>(employee, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<EmployeeResponse> employeeNotFoundException(EmployeeNotFoundException  employeeNotFoundException) {
		EmployeeResponse employee = new EmployeeResponse();
		exceptionReport = new ExceptionReport();
		getCurrentDateAndTime = new GetCurrentDateAndTime();
		
		
		// parsing status code, message and date and time to it.
		employee.setStatusCode((int) HttpStatus.NOT_FOUND.value());
		employee.setMessage(employeeNotFoundException.getMessage());
		employee.setDateTime(getCurrentDateAndTime.getCurrentFullDate());


		// setting the error response and updating database
		exceptionReport.setCode((int) employee.getStatusCode());
		exceptionReport.setException(employee.getMessage().toString());
		exceptionReport.setTime(employee.getDateTime());

		// saving all the exceptions to the database (using JPA repository).
		exceptionService.save(exceptionReport);

		return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<EmployeeResponse> exceptionHandler(Exception exception) {

		EmployeeResponse employee = new EmployeeResponse();
		exceptionReport = new ExceptionReport();

		// parsing status code, message and date and time to it.
		employee.setStatusCode((int) HttpStatus.BAD_REQUEST.value());
		employee.setMessage(exception.getMessage());
		employee.setDateTime(getCurrentDateAndTime.getCurrentFullDate());


		// setting the error response and updating database
		exceptionReport.setCode((int) employee.getStatusCode());
		exceptionReport.setException(employee.getMessage().toString());
		exceptionReport.setTime(employee.getDateTime());
		
		// saving all the exceptions to the database (using JPA repository).
		exceptionService.save(exceptionReport);

		return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
	}
}
