package com.gopolangmathole.employeedirectory.aspect;

import java.io.File;
import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gopolangmathole.employeedirectory.entity.EmployeeResponse;
import com.gopolangmathole.employeedirectory.entity.ExceptionReport;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.service.ExceptionService;

@Aspect
@Component
public class EmployeeAspectPathLogging {

	// auto wiring dependencies	
	@Autowired
	private ExceptionService exceptionService;

	@Pointcut("execution (* com.gopolangmathole.employeedirectory.service.EmployeeService.*(..))")
	public void getRestControllerAndController() {
	}

	// creating a before advice for controller
	@Before("getRestControllerAndController()")
	public void beforeAdviceController(JoinPoint jointPoint) {
		
		//creating objects
		EmployeeResponse employee = new EmployeeResponse();
		ExceptionReport exceptionReport = new ExceptionReport();
		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();

		// create log file
		try {

			// calling the file path method
			applicationFolders();

		} catch (IOException exception) {

			//setting status code and message
			employee.setStatusCode(500);
			employee.setMessage(exception.getMessage());
			employee.setDateTime(getCurrentDateAndTime.getCurrentFullDate());

			// setting the error response and updating database
			exceptionReport.setCode((int) employee.getStatusCode());
			exceptionReport.setException(employee.getMessage().toString());
			exceptionReport.setTime(employee.getDateTime());
			
			// saving all the exceptions to the database (using JPA repository).
			exceptionService.save(exceptionReport);

		}

	}

	//creating application folders and sub folders.
	public void applicationFolders() throws IOException {

		// parent directory
		File parent = new File("C:///employee_directory");

		// creating directory if it's not there.
		if (!(parent.exists())) {

			parent.mkdir();

		}

		// creating logs path
		File subLogs = new File("c:///employee_directory///logs");

		// if it doesn't exist let us make it
		if (!(subLogs.exists())) {

			subLogs.mkdirs();
		}

		// creating images path
		File subImages = new File("C:///employee_directory///images");

		// if it doesn't exist let us make it
		if (!(subImages.exists())) {

			subImages.mkdirs();
		}

		// creating reports path
		File subReports = new File("C:///employee_directory///reports");

		// if it doesn't exist let us make it
		if (!(subReports.exists())) {

			subReports.mkdirs();
		}

	}
}
