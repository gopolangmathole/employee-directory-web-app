package com.gopolangmathole.employeedirectory.rest;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gopolangmathole.employeedirectory.controller.EmployeeController;
import com.gopolangmathole.employeedirectory.entity.Employee;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.exception.EmployeeNotFoundException;
import com.gopolangmathole.employeedirectory.service.EmployeeService;

@RestController()
@RequestMapping("/api/v1")
@Api(name = "Employee Directory", description = "get all rest end-points to perform all CRUD actions", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class EmployeeRestController {

	// making use of the service
	@Autowired
	private EmployeeService employeeService;

	// expose "/employee end point to expose all employees
	@GetMapping("/employees")
	@ApiMethod(description = "get all employees which are currently available")
	public List<Employee> getAllEmployees() {

		return employeeService.findAll();
	}

	// Get employee by Id
	@GetMapping("/employees/{empId}")
	@ApiMethod(description = "get one employee from the provided id", path = { "empId" })
	public Employee getEmployee(
			@PathVariable @ApiPathParam(description = "input employee id to get employee", name = "empId") int empId)
			throws Exception {

		Employee tempEmployee = employeeService.findById(empId);

		if (tempEmployee == null) {

			throw new EmployeeNotFoundException("Employee id not found - " + empId);
		}

		return tempEmployee;
	}

	// end-point for adding new user
	@PostMapping("/employees")
	@ApiMethod(description = "add a new employee by passing jason data through the body")
	public Employee addEmployee(@RequestBody Employee employee) throws Exception {

		// creating a current date and time object
		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();

		// also just in case they pass an id in JSON...set id to 0
		// this is to force a save of new item...instead of update
		employee.setId(0);

		employee.getAddress().setId(0);

		// updating the last update column
		employee.setLastUpdate(getCurrentDateAndTime.getCurrentFullDate());

		// saving new employee
		employeeService.save(employee);

		return employee;
	}

	// end-point for updating new user
	@PutMapping("/employees")
	@ApiMethod(description = "update an existing employee by id")
	public Employee updateEmployee(@RequestBody Employee employee) throws Exception {

		// creating a current date and time object
		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();

		Employee theEmployee = employeeService.findById(employee.getId());

		if (theEmployee == null) {

			throw new EmployeeNotFoundException("Employee with id - " + employee.getId() + " doesn't exist");

		}

		// updating the last update column
		employee.setLastUpdate(getCurrentDateAndTime.getCurrentFullDate());

		// updating the address table id
		employee.getAddress().setId(employee.getId());

		// saving the object into the service
		employeeService.save(employee);

		// return the updated employee
		return employee;

	}

	// end-point for updating user image
	@PutMapping("/employees/{empId}")
	@ApiMethod(description = "update an existing employee's image by id")
	public Employee updateEmployee(@PathVariable int empId, @RequestPart("imageFile") MultipartFile imageFile)
			throws Exception {

		// creating a current date and time object
		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();
		Employee getEmployee = employeeService.findById(empId);

		if (getEmployee == null) {

			throw new EmployeeNotFoundException("Employee with id - " + empId + " doesn't exist");

		}

		// updating the last update column
		getEmployee.setLastUpdate(getCurrentDateAndTime.getCurrentFullDate());

		// updating the address table id
		getEmployee.getAddress().setId(getEmployee.getId());

		String currentImage = null;
		
		// saving an image
		employeeService.saveImage(imageFile, getEmployee, EmployeeController.uploadDirectory, currentImage);

		// saving the object into the service
		employeeService.save(getEmployee);

		// return the updated employee
		return getEmployee;

	}

	// end-point for deleting
	@DeleteMapping("/employees/{empId}")
	@ApiMethod(description = "Delete an existing employee by id", path = { "empId" })
	public String deleteEmployee(
			@PathVariable @ApiPathParam(description = "input employee id to delete", name = "empId") int empId)
			throws Exception {

		Employee tempEmployee = employeeService.findById(empId);

		if (tempEmployee == null) {

			throw new EmployeeNotFoundException("Employee id not found - " + empId);
		}

		employeeService.deleteById(empId);

		return "Employee with id '" + empId + "' was deleted";

	}

}
