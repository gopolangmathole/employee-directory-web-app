package com.gopolangmathole.employeedirectory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="street_address")
	private String streetAddress;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="country")
	private String country;
	
	
	/*
	 * Create no-arg constructor
	 * */
	
	public Address() {}

		
	
	public Address(String streetAddress, String city, String postalCode, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}
	

	public Address(int id, String streetName, String city, String postalCode, String country) {
		this.id = id;
		this.streetAddress = streetName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String StreetAddress) {
		this.streetAddress = StreetAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetAddress=" + streetAddress + ", city=" + city + ", postalCode=" + postalCode
				+ ", country=" + country + "]";
	}
	
}
