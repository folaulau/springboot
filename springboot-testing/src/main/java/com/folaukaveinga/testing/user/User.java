package com.folaukaveinga.testing.user;


import java.io.IOException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age;
	
	@Column(name="email")
	private String email;

	public User() {
		this(null,0, null);
	}
	public User(String name, int age, String email) {
		this(0,name,age, email);
	}
	
	public User(int id, String name, int age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).
			       append(name).
			       append(age).
			       append(email).
			       toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		User otherUser = (User)other;
		return new EqualsBuilder()
                .append(this.name, otherUser.name)
                .append(this.age, otherUser.age)
                .append(this.email, otherUser.email)
                .isEquals();
	}

	public static User fromJson(String json){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, User.class);
		} catch (IOException e) {
			return new User();
		}
	}

	public String toJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return"{}";
		}
	}
	
}
