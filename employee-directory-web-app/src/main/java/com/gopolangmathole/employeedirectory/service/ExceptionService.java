package com.gopolangmathole.employeedirectory.service;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionService {

	public void save(ExceptionReport exception);
	
	public long count();
}
