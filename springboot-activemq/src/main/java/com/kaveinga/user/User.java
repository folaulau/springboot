package com.kaveinga.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaveinga.utility.ObjectUtils;

@JsonInclude(value = Include.NON_NULL)
public class User {

	private String name;
	private String email;
	private Date timeStamp;
	
	
	
	public User() {
		this(null,null,null);
	}
	public User(String name, String email, Date timeStamp) {
		super();
		this.name = name;
		this.email = email;
		this.timeStamp = timeStamp;
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
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String toJson() {
        try {
            return ObjectUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException, msg: " + e.getLocalizedMessage());
            return "{}";
        }
    }
	
	 public static User fromJson(String json) {
	        try {
	            return ObjectUtils.getObjectMapper().readValue(json, User.class);
	        } catch (Exception e) {
	            System.out.println("Account - fromJson - Exception, msg: " + e.getLocalizedMessage());
	            return null;
	        }
	    }
	
}
