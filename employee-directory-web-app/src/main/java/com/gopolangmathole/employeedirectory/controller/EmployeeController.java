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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
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

	// property injection to auto wire our dependencies
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private EmployeeRepository employeeRepository;

	private List<String> selectGender;

	private CountryList countryList;

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/uploads";

	private String currentImage = null;

	private GetCurrentDateAndTime getCurrentDateAndTime;

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
	public String listEmployees(Model theModel, @RequestParam(defaultValue = "0") int page) {

		// getting the data from
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		// adding entities to the model
		theModel.addAttribute("number", countRows);
		theModel.addAttribute("reports", errorReport);
		theModel.addAttribute("employees", employeeRepository.findAll(PageRequest.of(page, 5)));
		//theModel.addAttribute("currentPage", page);

		return "/employees/list-employees";
	}

	@GetMapping("/all-employees")
	public String getAllEMployees(Model theModel) throws ParseException {
		// getting the data from
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();
		
		// adding entities to the model
		theModel.addAttribute("number", countRows);
		theModel.addAttribute("reports", errorReport);
		theModel.addAttribute("employees", employeeService.findAll());
		

		return "/employees/all-employees";
	}

	// add a new employee
	@GetMapping("/showFormForAdd")
	public String addEmployee(Model model) {

		// initializing arrayList for gender, and add both genders to list.
		selectGender = new ArrayList<String>();

		// initializing arrayList of countries
		countryList = new CountryList();

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

		getCurrentDateAndTime = new GetCurrentDateAndTime();

		if (errors.hasErrors()) {

			url = "/employees/employees-form";

		} else {

			// redirect to make sure the user doesn't refresh the page
			url = "redirect:/employees/list";

			// saving an image
			employeeService.saveImage(imageFile, employee, uploadDirectory, currentImage);

			//add employment status
			employee.setEmploymentStatus(true);
			
			// updating the last update column
			employee.setLastUpdate(getCurrentDateAndTime.getCurrentFullDate());

			// save the employee
			employeeRepository.save(employee);

		}

		// return the proper view
		return url;
	}

	@GetMapping("/edit/{id}")
	public String editEmployeeById(Model model, @ModelAttribute("id") int id) {

		Employee employee = employeeService.findById(id);
		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();
		// initializing arrayList for gender, and add both genders to list.
		selectGender = new ArrayList<String>();

		// initializing arrayList of countries
		countryList = new CountryList();

		// adding gender to list
		selectGender.add("Male");
		selectGender.add("Female");

		// setting and image to the variable to use it later
		currentImage = null;
		currentImage = employee.getImage();

		if (employee != null) {

			model.addAttribute("employee", employee);

		}

		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("genderSelected", selectGender);
		model.addAttribute("getCountries", countryList.getCountries());

		return "/employees/employees-form";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@ModelAttribute("id") int id, Model model) {

		Employee employee = employeeService.findById(id);

		if (employee != null) {

			employeeService.deleteById(id);
		}

		return "redirect:/employees/list";
	}

	@GetMapping("/api")
	public String getApis(Model model) {

		int countRows = (int) employeeService.count();
		int errorReport = (int) exceptionService.count();

		model.addAttribute("number", countRows);
		model.addAttribute("reports", errorReport);
		model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));

		return "/employees/list-apis";
	}

	@GetMapping("/viewReport")
	public String viewReport() {

		return "/dashboard/report";
	}

	@GetMapping("/viewReport/charts")
	public String getCharts() {

		return "/dashboard/charts";
	}

	@GetMapping("/viewReport/tables")
	public String getTables() {

		return "/dashboard/tables";
	}

}
