package com.gopolangmathole.employeedirectory.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetRequiredDate {

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
