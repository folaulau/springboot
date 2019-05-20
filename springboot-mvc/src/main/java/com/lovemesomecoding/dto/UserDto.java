package com.lovemesomecoding.dto;

public class UserDto {
	
	private String uid;

	private String name;

	private String email;

	private int age;
	
	public UserDto() {
		this(null,null,null,0);
	}

	public UserDto(String uid, String name, String email, int age) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.age = age;
	}



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
