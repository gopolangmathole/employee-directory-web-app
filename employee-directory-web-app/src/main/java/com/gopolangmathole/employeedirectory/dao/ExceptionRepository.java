package com.gopolangmathole.employeedirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionRepository extends JpaRepository<ExceptionReport, Integer> {

	// doing count for all the rows
	public long count();

	// using 400 error code
	@Query("SELECT count(*) FROM ExceptionReport where code= 400")
	public long get400Error();

	// using 404 error code
	@Query("SELECT count(*) FROM ExceptionReport where code= 404")
	public long get404Error();

	// using 500 error code
	@Query("SELECT count(*) FROM ExceptionReport where code= 500")
	public long get500Error();

	@Query("SELECT count(*) FROM ExceptionReport where code != 400 and code != 404 and code <> 500")
	public long getOtherError();

	
}
