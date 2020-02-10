package com.gopolangmathole.employeedirectory.rest;

import java.util.ArrayList;
import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopolangmathole.employeedirectory.entity.GetCurrentDateAndTime;
import com.gopolangmathole.employeedirectory.service.ExceptionService;

@RestController()
@RequestMapping("/api/v1")
@Api(visibility = ApiVisibility.PRIVATE, description = "", name = "")
public class ErrorRestController {

	// dependency injection
	@Autowired
	ExceptionService exceptionService;

	@GetMapping("/health/error_count")

	// get error count
	public int getErrorsCount() {
		// returning total error count
		return (int) exceptionService.count();
	}

	// health mapping
	@GetMapping("/health/combined_count")
	public List<Integer> getCombine() {

		// adding the list
		List<Integer> errorCode = new ArrayList<>();

		// adding all custom queries to the model.
		errorCode.add((int) exceptionService.get400Error());
		errorCode.add((int) exceptionService.get404Error());
		errorCode.add((int) exceptionService.get500Error());
		errorCode.add((int) exceptionService.getOtherError());

		// adding the list to the mode
		return errorCode;
	}

	// data for the y-axis
	@GetMapping("/health/graph_data/y-axis")
	public List<Integer> getYaxisGraphData() {

		// getting the date converter class
		GetCurrentDateAndTime date = new GetCurrentDateAndTime();

		// adding a list for the graph
		List<Integer> dataY = new ArrayList<>();

		// adding 9 days data to the list
		dataY.add((int) exceptionService.getTimeCount(date.getDate(0)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(1)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(2)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(3)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(4)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(5)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(6)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(7)));
		dataY.add((int) exceptionService.getTimeCount(date.getDate(8)));

		// adding data to the list
		return dataY;

	}

	@GetMapping("/health/graph_data/x-axis")
	public List<String> getXaxisGraphData() {
		// getting the date converter class
		GetCurrentDateAndTime date = new GetCurrentDateAndTime();

		// adding a list for the graph
		List<String> dataX = new ArrayList<>();

		// adding data to the list
		dataX.add("Today");
		dataX.add("Yesterday");
		dataX.add(date.getDate(2));
		dataX.add(date.getDate(3));
		dataX.add(date.getDate(4));
		dataX.add(date.getDate(5));
		dataX.add(date.getDate(6));
		dataX.add(date.getDate(7));
		dataX.add(date.getDate(8));

		// returning the list
		return dataX;

	}
}
