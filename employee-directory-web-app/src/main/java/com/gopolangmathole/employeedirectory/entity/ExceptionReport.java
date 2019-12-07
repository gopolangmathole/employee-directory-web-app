package com.gopolangmathole.employeedirectory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="exception")
public class ExceptionReport {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="code")
	private int code;
	
	@Column(name="exception")
	private String exception;
	
	@Column(name="time")
	private String time;
	
	public ExceptionReport() {}

	public ExceptionReport(int code, String exception, String time) {
		this.code = code;
		this.exception = exception;
		this.time = time;
	}

	public ExceptionReport(int id, int code, String exception, String time) {
		this.id = id;
		this.code = code;
		this.exception = exception;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Exception [id=" + id + ", code=" + code + ", exception=" + exception + ", time=" + time + "]";
	}
	

	
}
