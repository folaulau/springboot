package com.kaveinga.mapping.dto;

import java.util.Date;

import com.kaveinga.mapping.utility.ObjectUtils;

public class UserCreateDTO {

	private String email;
	private String firstName;
	private String lastName;
	private Date dob;
	
	public UserCreateDTO() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return ObjectUtils.toJson(this);
	}
	
	
}
