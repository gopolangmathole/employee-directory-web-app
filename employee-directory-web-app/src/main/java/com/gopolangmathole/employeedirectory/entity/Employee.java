package com.gopolangmathole.employeedirectory.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiVisibility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
@Api(visibility = ApiVisibility.PRIVATE, description = "", name = "")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiObjectField
	private int id;

	@NotNull
	@Size(min = 2, max = 50, message = "Length should be between 2 and 50")
	@Column(name = "first_name")
	@ApiObjectField(description = "Employee first name")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 50, message = "Length should be between 2 and 50")
	@Column(name = "last_name")
	@ApiObjectField(description = "Employee last Name")
	private String lastName;

	@Column(name = "gender")
	@NotNull
	@ApiObjectField(description = "Employee's sex")
	private String gender;

	@NotNull
	@Email(regexp = "^(.+)@(.+)$", message = "Invalid email address")
	@Column(name = "email")
	@ApiObjectField(description = "Employee's email")
	private String email;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Column(name = "image")
	@ApiObjectField(description = "Employee's image")
	private String image;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Column(name = "last_update")
	@ApiObjectField(description = "Employee's update")
	private String lastUpdate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_address_id")
	@ApiObjectField(description = "Employee's address")
	private Address address;

	// define constructors
	public Employee() {

	}

	public Employee(Address address) {

		this.address = address;
	}

	public Employee(int id, String firstName, String image, String lastName, String gender, String email,
			String lastUpdate, Address employeeAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.image = image;
		this.lastUpdate = lastUpdate;
		this.address = employeeAddress;
	}

	public Employee(String firstName, String image, String lastName, String gender, String email, String lastUpdate,
			 Address employeeAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.image = image;
		this.lastUpdate = lastUpdate;
		this.address = employeeAddress;
	}

	// define getter/setter
	public int getId() {
		return id;
	}

	
	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String string) {
		this.image = string;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(Address employeeAddress) {

		this.address = employeeAddress;
	}

	public Address getAddress() {

		return address;
	}

	// define tostring
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", image=" + image + ", lastUpdate=" + lastUpdate + ", address=" + address + "]";
	}

}
