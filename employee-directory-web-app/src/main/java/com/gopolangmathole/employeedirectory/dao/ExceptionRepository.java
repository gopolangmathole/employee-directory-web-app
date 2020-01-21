package com.gopolangmathole.employeedirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionRepository extends JpaRepository<ExceptionReport, Integer> {

	// doing count for all the rows
	public long count();

	// using 400 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport where code= 400")
	public long get400Error();

	// using 404 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport where code= 404")
	public long get404Error();

	// using 500 error code
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code= 500")
	public long get500Error();

	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE code != 400 and code != 404 and code <> 500")
	public long getOtherError();

	// graph queries

	// getting three days back date

	// get today's data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE time BETWEEN DATE_SUB(NOW(), INTERVAL 1 DAY) AND NOW()")
	public long getToday();

	// get yesterday's data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE time between subdate(curdate(), 1)and curdate()")
	public long getYesterday();

	// get three days back data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE TO_DAYS(`time`) < TO_DAYS(NOW())-1")
	public long getThreeDaysBack();

	// get four days back data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE TO_DAYS(`time`) < TO_DAYS(NOW())-2;")
	public long getFourDaysBack();

	// get five day back data
	@Query("SELECT COUNT(*) FROM ExceptionReport WHERE TO_DAYS(`time`) < TO_DAYS(NOW())-3;")
	public long getFiveDaysBack();

}
