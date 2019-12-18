package com.gopolangmathole.employeedirectory.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetCurrentDateAndTime {

	//creating a method for date and time so we can re-use it
	public String getCurrentFullDate() {
		
		// get current date and time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
				
		
		//return the date
		return dtf.format(now).toString();		
	}
}
