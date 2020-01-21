package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopolangmathole.employeedirectory.dao.ExceptionRepository;
import com.gopolangmathole.employeedirectory.entity.ExceptionReport;

@Service
public class ExceptionServiceImpl implements ExceptionService {

	// injecting the exception repository
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

	@Override
	public List<ExceptionReport> findAll() {

		return exceptionRepository.findAll();
	}

	@Override
	public long get400Error() {

		return exceptionRepository.get400Error();
	}

	@Override
	public long get404Error() {

		return exceptionRepository.get404Error();
	}

	@Override
	public long get500Error() {

		return exceptionRepository.get500Error();
	}

	@Override
	public long getOtherError() {

		return exceptionRepository.getOtherError();
	}

	@Override
	public long getToday() {

		return exceptionRepository.getToday();
	}

	@Override
	public long getYesterday() {
		
		return exceptionRepository.getYesterday();
	}

	@Override
	public long getThreeDaysBack() {
		
		return exceptionRepository.getThreeDaysBack();
	}

	@Override
	public long getFourDaysBack() {
		
		return exceptionRepository.getFourDaysBack();
	}

	@Override
	public long getFiveDaysBack() {
		
		return exceptionRepository.getFiveDaysBack();
		
	}

}
