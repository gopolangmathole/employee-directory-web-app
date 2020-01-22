package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionService {

	// get all exception
	public List<ExceptionReport> findAll();

	// save exceptions
	public void save(ExceptionReport exception);

	// get count
	public long count();

	// using 400 error code
	public long get400Error();

	// using 404 error code
	public long get404Error();

	// using 500 error code
	public long get500Error();

	// getting other codes
	public long getOtherError();

	
	// get today's data
	//public long getToday();

	/*
	// get yesterday's data
	public long getYesterday();

	// get three days back data
	public long getThreeDaysBack();

	// get four days back data
	public long getFourDaysBack();

	// get five day back data
	public long getFiveDaysBack();
*/
}
