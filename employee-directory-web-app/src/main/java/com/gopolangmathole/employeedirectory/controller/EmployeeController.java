package com.gopolangmathole.employeedirectory.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gopolangmathole.employeedirectory.dao.EmployeeRepository;
import com.gopolangmathole.employeedirectory.entity.CountryList;
import com.gopolangmathole.employeedirectory.entity.Employee;
import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.service.EmployeeService;
import com.gopolangmathole.employeedirectory.service.ExceptionService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	//property injection to auto wire our dependencies
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private EmployeeRepository employeeRepository;

	//creating absolute path
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/uploads";

	private String currentImage = null;

	// add an in-it-binder ... to convert trim input string
	// remove leading and trailing whitespace
	// resolve issue
	@InitBinder
	public void initBinder(WebDataBinder databinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);

	}

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model model, @RequestParam(defaultValue = "0") int page) {

		// getting the data from
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		// adding entities to the model
		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("employees", employeeRepository.findAll(PageRequest.of(page, 5)));

		return "/employees/list-employees";
	}

	@GetMapping("/all-employees")
	public String getAllEMployees(Model model) throws ParseException {
		// getting the data from
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		// adding entities to the model
		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("updateStatus", employeeService.getEmployeeLastUpdate());

		return "/employees/all-employees";
	}

	// add a new employee
	@GetMapping("/showFormForAdd")
	public String addEmployee(Model model) {

		// initializing arrayList for gender, and add both genders to list.
		List<String> selectGender = new ArrayList<String>();

		// initializing arrayList of countries
		CountryList countryList = new CountryList();

		// adding gender to list
		selectGender.add("Male");
		selectGender.add("Female");

		// create model attribute to bind form data
		Employee employee = new Employee();
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		// making sure a new user doesn't have the profile picture of the previous user
		currentImage = null;

		// adding objects to model
		model.addAttribute("employee", employee);
		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("genderSelected", selectGender);
		model.addAttribute("getCountries", countryList.getCountries());

		return "/employees/employees-form";

	}

	// save employees
	@PostMapping("/save")
	public String saveEmployee(@RequestParam("imageFile") MultipartFile imageFile,
			@ModelAttribute("employee") @Valid Employee employee, Errors errors) throws IOException {

		String url = null;

		GetCurrentDateAndTime getCurrentDateAndTime = new GetCurrentDateAndTime();

		if (errors.hasErrors()) {

			url = "/employees/employees-form";

		} else {

			// redirect to make sure the user doesn't refresh the page
			url = "redirect:/employees/list";

			// saving an image
			employeeService.saveImage(imageFile, employee, uploadDirectory, currentImage);

			// updating the last update column
			employee.setLastUpdate(getCurrentDateAndTime.getCurrentFullDate());

			// updating the address table id
			employee.getAddress().setId(employee.getId());

			// save the employee
			employeeService.save(employee);

		}

		// return the proper view
		return url;
	}

	//adding the get mapping for editing 
	@GetMapping("/edit/{id}")
	public String editEmployeeById(Model model, @ModelAttribute("id") int id) {

		//getting the employee based on the id given and counting errors.
		Employee employee = employeeService.findById(id);
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();
		
		// initializing arrayList for gender, and add both genders to list.
		List<String> selectGender = new ArrayList<String>();

		// initializing arrayList of countries
		CountryList countryList = new CountryList();

		// adding gender to list
		selectGender.add("Male");
		selectGender.add("Female");

		// setting and image to the variable to use it later
		currentImage = null;
		currentImage = employee.getImage();

		//adding the returned employee to the model
		if (employee != null) {

			model.addAttribute("employee", employee);

		}

		//adding respective details to the model.
		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("genderSelected", selectGender);
		model.addAttribute("getCountries", countryList.getCountries());

		//returning the view
		return "/employees/employees-form";
	}

	//adding delete mapping
	@GetMapping("/delete/{id}")
	public String deleteUser(@ModelAttribute("id") int id, Model model) {

		//passing the id of the employee which should be deleted
		Employee employee = employeeService.findById(id);

		//deleting the employee from the database provided the specified user id was found.
		if (employee != null) {

			employeeService.deleteById(id);
		}

		//returning the list view.
		return "redirect:/employees/list";
	}

	//setting up api mapping
	@GetMapping("/api")
	public String getApis(Model model) {

		// getting the number of rows for errors counts and employee services
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		// adding the objects to the model
		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));

		// returning the view
		return "/employees/list-apis";
	}

	// adding the get report mapping
	@GetMapping("/viewReport")
	public String viewReport(Model model) {

		// getting all errors from the database
		int errorReport = (int) exceptionService.count();

		// adding the object to the model
		model.addAttribute("exceptions", exceptionService.findAll());
		model.addAttribute("reports", errorReport);
		model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));

		// returning view
		return "/dashboard/report";
	}

}
