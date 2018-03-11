package com.folaukaveinga.api.model;

import java.io.Serializable;
import java.util.List;

public class User  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String password;
	private List<String> roles;
	
	
	public User() {
		this(0);
	}
	
	public User(long id) {
		this(id,null,null,null);
	}
	
	public User(long id, String email, String password, List<String> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
