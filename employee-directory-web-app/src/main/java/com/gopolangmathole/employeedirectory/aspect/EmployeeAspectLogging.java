package com.gopolangmathole.employeedirectory.aspect;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.entity.HttpServletRequestsLogging;
import com.gopolangmathole.employeedirectory.service.EmployeeLoggingService;

@Aspect
@Component
public class EmployeeAspectLogging {

	// declaring the polo or entity so we can populate the setter and getters.
	private HttpServletRequestsLogging requestsLogging;

	private ExceptionReport exceptionReport;

	// should inject database logging for all requests.
	@Autowired
	private EmployeeLoggingService employeeLoggingService;

	// using spring monitor requests
	@Autowired(required = false)
	private HttpServletRequest request;

	@Pointcut("execution (* com.gopolangmathole.employeedirectory.service.EmployeeService.*(..))")
	public void getRestControllerAndController() {
	}

	// creating a before advice for controller
	@Before("getRestControllerAndController()")
	public void beforeAdviceController(JoinPoint jointPoint) {

		requestsLogging = new HttpServletRequestsLogging();
		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();

		// parsing data to the setters.
		requestsLogging.setDateTime(getCurrentDateAndTime.getCurrentFullDate());

		// create log file
		try {

			requestsLogging.setRequest(request.getRequestURL().toString());
			applicationLogFiles(jointPoint, getCurrentDateAndTime.getCurrentFullDate());

		} catch (IOException exception) {

			exceptionReport.setException(exception.getMessage());
			exceptionReport.setTime(getCurrentDateAndTime.getCurrentFullDate());
			exceptionReport.setCode(500);
		}

		// save data into the database by using the object
		employeeLoggingService.save(requestsLogging);

	}

	public void applicationLogFiles(JoinPoint jointPoint, String date) throws IOException {

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