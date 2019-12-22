package com.gopolangmathole.employeedirectory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gopolangmathole.employeedirectory.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//adding descending or Id
	//public List<Employee> findAllByIdDesc();
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	public List<Employee> findAllByOrderByLastNameDesc();
	
	//adding sort my first name
	public List<Employee> findAllByOrderByFirstNameAsc();
	public List<Employee> findAllByOrderByFirstNameDesc();
	
	//adding count, but it's already available with spring boot
	public long count();

	//using query builder
	@Query("select s.lastUpdate from Employee s")
	public List<String> getEmployeeLastUpdate();
}
