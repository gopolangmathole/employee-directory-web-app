package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

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

	//get error counts for given date
	public long getTimeCount(@Param("date") String date);
}
