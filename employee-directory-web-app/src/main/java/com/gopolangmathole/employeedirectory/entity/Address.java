package com.gopolangmathole.employeedirectory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="employee_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Column(name="street_address")
	private String streetAddress;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Column(name="city")
	private String city;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Column(name="postal_code")
	private String postalCode;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
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

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
