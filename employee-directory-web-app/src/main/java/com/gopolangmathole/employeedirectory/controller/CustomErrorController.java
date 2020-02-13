package com.gopolangmathole.employeedirectory.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	//creating custom error code
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		//checking the status
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			//return a 404 with model data
			if (statusCode == HttpStatus.NOT_FOUND.value()) {

				model.addAttribute("error", (int) HttpStatus.NOT_FOUND.value());
				model.addAttribute("message","Page not found");
				model.addAttribute("submessage","The page you are looking for might have been removed or is temporarily unavailable.");
			}
			//return a 500 with model data
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("error", (int) HttpStatus.INTERNAL_SERVER_ERROR.value());
				model.addAttribute("message","Ooops server down");
				model.addAttribute("submessage","Sorry for the inconvenience this may have cause, our technicians are working tirelessly to resolve the issue.");

			} 
			//return a 404 with model data
			else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
				model.addAttribute("error", (int) HttpStatus.BAD_REQUEST.value());
				model.addAttribute("message","Bad request");
				model.addAttribute("submessage","The was an error processing your request, please rectify your mistake(s).");
			}
		}
		return "error";
	}
	
	// adding error page
		@Override
		public String getErrorPath() {

			return "/error";
		}


}
