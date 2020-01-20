package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionService {

	//get all exception
	public List<ExceptionReport> findAll();
	
	//save exceptions
	public void save(ExceptionReport exception);
	
	//get count
	public long count();
}
