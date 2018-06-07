package com.kaveinga.user;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user", type = "user")
public class User implements Serializable{

	private static final long serialVersionUID= 1L;

	@Id
	private Long id;
	
	@Field(index=true,type=FieldType.Text)
	private String name;
	
	@Field(index=true,type=FieldType.Text)
	private String email;
	
	public User() {
		this(null);
	}
	
	public User(Long id) {
		this(id,null,null);
	}
	
	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
