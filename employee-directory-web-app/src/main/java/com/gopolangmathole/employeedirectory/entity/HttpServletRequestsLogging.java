package com.gopolangmathole.employeedirectory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_logging")
public class HttpServletRequestsLogging {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="request")
	private String request;
	
	@Column(name="date")
	private String dateTime;
	
	//no-arg constructor
	public HttpServletRequestsLogging() {}

	public HttpServletRequestsLogging(String request, String dateTime) {
		this.request = request;
		this.dateTime = dateTime;
	}
	
	public HttpServletRequestsLogging(int id, String request, String dateTime) {
		this.id = id;
		this.request = request;
		this.dateTime = dateTime;
	}
	
	//setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	//to-String to debug and see the method's response
	@Override
	public String toString() {
		return "HttpServletRequestsLogging [id=" + id + ", request=" + request + ", dateTime=" + dateTime + "]";
	}
	

	
	
}
