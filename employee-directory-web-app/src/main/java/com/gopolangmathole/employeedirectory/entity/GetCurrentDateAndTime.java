package com.gopolangmathole.employeedirectory.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentDateAndTime {

	//creating a method for date and time so we can re-use it
	public String getCurrentFullDate() {
		
		// get current date and time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
				
		
		//return the date
		return dtf.format(now).toString();		
	}

	//get the date to the day parsed into the method
	public String getDate(int day) {

		//formatting time and date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//getting current date
		Date currentDate = new Date();

		// convert date to calendar
		Calendar calendar = Calendar.getInstance();

		//setting current date to me
		calendar.setTime(currentDate);

		//getting the date required
		calendar.add(Calendar.DATE, -(day)); 

		// convert calendar to date
		Date getRequiredDay = calendar.getTime();

		//return required date
		return dateFormat.format(getRequiredDay).toString();

	}

}
