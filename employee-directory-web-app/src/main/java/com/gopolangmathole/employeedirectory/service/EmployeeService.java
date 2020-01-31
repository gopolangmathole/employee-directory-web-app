package com.gopolangmathole.employeedirectory.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gopolangmathole.employeedirectory.entity.Employee;

import net.sf.jasperreports.engine.JRException;


public interface EmployeeService {

	public List<Employee> findAll();
	
	//adding descending or Id
	//public List<Employee> findAllByIdDesc();
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	public List<Employee> findAllByOrderByLastNameDesc();
		
	//adding sort my first name
	public List<Employee> findAllByOrderByFirstNameAsc();
	public List<Employee> findAllByOrderByFirstNameDesc();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public long count();

	public void saveImage(MultipartFile imageFile, Employee employee, String uploadDirectory, String currentImage) throws IOException;
	
	public List<String> getEmployeeLastUpdate() throws ParseException;
	
	public void generateReport(String requiredFormat) throws IOException,JRException;
}
