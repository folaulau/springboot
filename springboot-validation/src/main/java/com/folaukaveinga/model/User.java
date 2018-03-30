package com.folaukaveinga.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class User {
	
	private String email;
	
	private String name;

	private int age;
	
	private String role;
	
	private Date dob;

	public User() {
		super();
	}
	
	public User(String name) {
		this.name = name;
	}

	public User(String email, String name, int age, String role, Date dob) {
		super();
		this.email = email;
		this.name = name;
		this.age = age;
		this.role = role;
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", age=" + age + ", role=" + role + ", dob=" + dob + "]";
	}

	
}
