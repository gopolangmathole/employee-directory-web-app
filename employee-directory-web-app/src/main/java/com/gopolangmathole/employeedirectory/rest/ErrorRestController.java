package com.gopolangmathole.employeedirectory.rest;

import java.util.ArrayList;
import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopolangmathole.employeedirectory.service.ExceptionService;

@RestController()
@RequestMapping("/api/v1")
@Api(visibility = ApiVisibility.PRIVATE, description = "", name = "")
public class ErrorRestController {

	//dependency injection
	@Autowired
	ExceptionService exceptionService;

	@GetMapping("/health/error_count")

	//get error count
	public int getErrorsCount() {
		//returning total error count
		return (int) exceptionService.count();
	}

	//health mapping
	@GetMapping("/health/combined_count")
	public List<Integer> getCombine() {

		//adding the list 
		List<Integer> errorCode = new ArrayList<>();

		// adding all custom queries to the model.
		errorCode.add((int) exceptionService.get400Error());
		errorCode.add((int) exceptionService.get404Error());
		errorCode.add((int) exceptionService.get500Error());
		errorCode.add((int) exceptionService.getOtherError());

		// adding the list to the mode
		return errorCode;
	}
	
	@GetMapping("/health/graph_data")
	public List<Integer> getGraphData(){
		
		//adding a list for the graph
		List <Integer> graphData = new ArrayList<>();
		
		//adding data to the list
		graphData.add((int) exceptionService.getToday());
		graphData.add((int) exceptionService.getYesterday());
		graphData.add((int) exceptionService.getThreeDaysBack());
		graphData.add((int) exceptionService.getFourDaysBack());
		graphData.add((int) exceptionService.getFiveDaysBack());
	
		return graphData;
	}
}
