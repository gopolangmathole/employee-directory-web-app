package com.gopolangmathole.employeedirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopolangmathole.employeedirectory.dao.ExceptionRepository;
import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

@Service
public class ExceptionServiceImpl implements ExceptionService {

	@Autowired
	private ExceptionRepository exceptionRepository;
	
	@Override
	public void save(ExceptionReport exception) {
		
		exceptionRepository.save(exception);
	}

	@Override
	public long count() {
		
		return exceptionRepository.count();
	}



	
	

}
