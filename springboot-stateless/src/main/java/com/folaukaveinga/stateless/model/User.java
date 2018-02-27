package com.folaukaveinga.stateless.model;

public class User {
	private long id;
	private String name;
	private String email;
	private String phone;
	private String status;
	
	
	public User() {
		this(0);
	}
	
	public User(long id) {
		this(id,null,null,null,null);
	}
	
	public User(long id, String name, String email, String phone, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
