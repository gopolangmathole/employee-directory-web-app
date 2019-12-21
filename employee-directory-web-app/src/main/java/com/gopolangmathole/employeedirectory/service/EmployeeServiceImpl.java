package com.gopolangmathole.employeedirectory.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gopolangmathole.employeedirectory.dao.EmployeeRepository;
import com.gopolangmathole.employeedirectory.entity.Employee;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// injecting the required dependency
	@Autowired
	private EmployeeRepository employeeRepository;

	// setting final path to store the images
	private final String FOLDER = "c:///employee_directory//images///";

	// getting current date
	private GetCurrentDateAndTime getCurrentDateAndTime;
	
	//the return date
	List <String> returnDate;
	
	@Override
	public List<Employee> findAll() {

		// return employeeRepository.findAllByOrderByLastNameAsc();

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

		if (!(theEmployee.isPresent())) {

			throw new EmployeeNotFoundException("Did not find employee id with id -  " + theId);
		}

		employeeRepository.deleteById(theId);
	}

	@Override
	public long count() {

		return employeeRepository.count();
	}

	/*
	 * @Override public List<Employee> findAllByIdDesc() {
	 * 
	 * return employeeRepository.findAllByIdDesc(); }
	 */

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

	// save an image here
	@Override
	public void saveImage(MultipartFile imageFile, Employee employee, String uploadDirectory, String currentImage)
			throws IOException {

		// saving an image now
		byte[] bytes;

		long millis = System.currentTimeMillis();

		bytes = imageFile.getBytes();

		String modifiedFileName = (millis + "_" + imageFile.getOriginalFilename()).toLowerCase().replaceAll(" ", "_");

		// Writing to local disk space
		Path path = Paths.get(FOLDER + modifiedFileName);

		// storing image on web application.
		Path localPath = Paths.get(uploadDirectory, modifiedFileName);

		// checking if the file path is null or empty, we don't want to be saving empty
		// none-existing images
		if (imageFile.getSize() > 0) {

			// writing the file to specified path
			Files.write(path, bytes);

			// saving to app file
			Files.write(localPath, bytes);

			// saving image to database
			employee.setImage(modifiedFileName.toString());

		} else {

			// if the path is empty, let us set the old picture
			employee.setImage(currentImage);

		}
	}

	@Override
	public List<String> getEmployeeLastUpdate() throws ParseException {

		// initializing current date class
		getCurrentDateAndTime = new GetCurrentDateAndTime();

		List<String> update = employeeRepository.getEmployeeLastUpdate();
		String currentDate = getCurrentDateAndTime.getCurrentFullDate();
		returnDate = new ArrayList<String>(); 

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		
		Date d1 = null;
		Date d2 = null;
		
		//clearing the list to avoid redundancy 
		returnDate.clear();
		
		for(String date : update ){
			
			d1 = format.parse(date);
			d2 = format.parse(currentDate);
			
			long diff = (d2.getTime() - d1.getTime());
			
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
		
			if(diffSeconds <=59 && diffMinutes ==0 && diffHours==0 && diffDays==0) {
				
				returnDate.add((diffSeconds)+" seconds ago");
			
			}else if(diffMinutes <= 59 && diffHours==0 && diffDays==0) {
				
				returnDate.add((diffMinutes)+" minutes ago");
			
			}else if(diffHours <=59 && diffMinutes <=59 &&  diffDays==0) {
				
				returnDate.add((diffHours)+" hours ago");
			
			}else if(diffDays <=7 && diffMinutes <=59) {
				
				returnDate.add((diffDays)+" days ago");
			}else {
				
				returnDate.add(date);
			}
		}
		
		return returnDate;
	}

}
