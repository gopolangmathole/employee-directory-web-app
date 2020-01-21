package com.gopolangmathole.employeedirectory.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController{


	//adding mapping for error
	@RequestMapping("/error")
    public String handleError() {
        
        return "error";
    }
	
	//adding error page
	@Override
	public String getErrorPath() {
		
		return "/error";
	}

}
