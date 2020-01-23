package com.gopolangmathole.employeedirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionRepository extends JpaRepository<ExceptionReport, Integer> {

	/*
	 * Data for the charts
	 */

	// doing count for all the rows
	public long count();

	// using 400 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code= 400")
	public long get400Error();

	// using 404 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code= 404")
	public long get404Error();

	// using 500 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code= 500")
	public long get500Error();

	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code != 400 and code != 404 and code <> 500")
	public long getOtherError();

	/*
	 * Data for the Graph
	 * 
	 */

	// get required date data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE time like :date%")
	public long getTimeCount(@Param("date") String date);
}
