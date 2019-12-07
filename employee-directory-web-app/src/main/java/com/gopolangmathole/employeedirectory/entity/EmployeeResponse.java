package com.gopolangmathole.employeedirectory.entity;

public class EmployeeResponse {

	private int statusCode;
	private String message;
	private String dateTime;

	public EmployeeResponse() {
	}

	public EmployeeResponse(int statusCode, String message, String dateTime) {

		this.statusCode = statusCode;
		this.message = message;
		this.dateTime = dateTime;
	}
	

	/*
	 * getter and setter
	 */

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
