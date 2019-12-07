package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import com.gopolangmathole.employeedirectory.entity.HttpServletRequestsLogging;

public interface EmployeeLoggingService {

	public List<HttpServletRequestsLogging> findAll();
	
	public void save(HttpServletRequestsLogging httpServletRequestsLogging);
	
}
