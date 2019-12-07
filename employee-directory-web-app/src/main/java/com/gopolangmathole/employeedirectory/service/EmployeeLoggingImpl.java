package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopolangmathole.employeedirectory.dao.EmployeeLoggingRepository;
import com.gopolangmathole.employeedirectory.entity.HttpServletRequestsLogging;

@Service
public class EmployeeLoggingImpl implements EmployeeLoggingService {

	// dependency injection
	@Autowired
	EmployeeLoggingRepository employeeLoggingRepository;
	

	// fetch all rows from the database
	@Override
	public List<HttpServletRequestsLogging> findAll() {
		
		return employeeLoggingRepository.findAll();
	}

	//save all the information into the database to track user activity.
	@Override
	public void save(HttpServletRequestsLogging httpServletRequestsLogging) {
		
		employeeLoggingRepository.save(httpServletRequestsLogging);
	}

}
