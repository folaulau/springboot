package com.kaveinga.mapping.dto;

import java.util.Date;

import com.kaveinga.mapping.utility.ObjectUtils;

public class UserMobDTO {

	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String name;
	private Date dob;
	
	public UserMobDTO() {
		super();
	}
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
