package com.gopolangmathole.employeedirectory.entity;

import java.util.ArrayList;
import java.util.List;

public class CountryList {

	//declaring arrayList
	private List<String> contries;
	
	//no-arg constructor
	public CountryList() {}
	
	//creating a constructor which accepts the list
	public CountryList(List<String> contries) {
		
		this.contries = contries;
	}
	
	public List<String> getCountries() {
		
		//initializing arrayList here
		contries = new ArrayList<String>();
		
		//adding countries onto the list
		contries.add("South Africa");
		contries.add("Botswana");
		contries.add("Nigeria");
		contries.add("Zambia");
		contries.add("Algeria");
		contries.add("Gabon");
		contries.add("Ghana");
		contries.add("Morocco");
		contries.add("Tanzania");
		contries.add("Namibia");
		contries.add("Madagascar");
		contries.add("Kenya");
		contries.add("Switzerland");
		contries.add("Japan");
		contries.add("Canada");
		contries.add("Germany");
		contries.add("United Kingdom");
		contries.add("Sweden");
		contries.add("Australia");
		contries.add("United States");
		contries.add("France");
		contries.add("Norway");
		
		// return the list
		return contries;
	} 
}
