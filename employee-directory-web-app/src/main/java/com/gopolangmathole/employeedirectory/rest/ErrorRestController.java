package com.gopolangmathole.employeedirectory.rest;

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
	
	@Autowired
	ExceptionService exceptionService;
	
	@GetMapping("/health/error_count")
	
	public int getErrorsCount() {
		
		return (int) exceptionService.count();
	}

}
