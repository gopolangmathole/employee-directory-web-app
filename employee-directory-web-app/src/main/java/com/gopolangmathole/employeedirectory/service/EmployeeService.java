package com.gopolangmathole.employeedirectory.service;

import java.util.List;

import com.gopolangmathole.employeedirectory.entity.Employee;


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

	//public 
	
}
