package com.gopolangmathole.employeedirectory.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopolangmathole.employeedirectory.dao.EmployeeRepository;
import com.gopolangmathole.employeedirectory.entity.Employee;
import com.gopolangmathole.employeedirectory.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		
		//return employeeRepository.findAllByOrderByLastNameAsc();
		
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			
			throw new EmployeeNotFoundException("Did not find employee id with id -  " + theId);
		}

		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		employeeRepository.save(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {

		Optional<Employee> theEmployee = employeeRepository.findById(theId);

		if(!(theEmployee.isPresent())) {
			
			throw new EmployeeNotFoundException("Did not find employee id with id -  " + theId);
		}
		
		employeeRepository.deleteById(theId);
	}

	@Override
	public long count() {
		
		return employeeRepository.count();
	}

	/*@Override
	public List<Employee> findAllByIdDesc() {
		
		return employeeRepository.findAllByIdDesc();
	}*/

	@Override
	public List<Employee> findAllByOrderByLastNameAsc() {
		
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public List<Employee> findAllByOrderByLastNameDesc() {
		
		return employeeRepository.findAllByOrderByLastNameDesc();
	}

	@Override
	public List<Employee> findAllByOrderByFirstNameAsc() {
		
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public List<Employee> findAllByOrderByFirstNameDesc() {
		
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}


	

}
