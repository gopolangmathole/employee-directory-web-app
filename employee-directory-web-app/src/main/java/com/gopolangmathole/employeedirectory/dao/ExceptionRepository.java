package com.gopolangmathole.employeedirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

public interface ExceptionRepository extends JpaRepository<ExceptionReport ,Integer > {

	public long count();
	
}
