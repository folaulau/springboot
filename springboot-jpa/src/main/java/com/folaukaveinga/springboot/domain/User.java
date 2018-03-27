package com.folaukaveinga.springboot.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(value = Include.NON_NULL)
// @Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="user-region")
// @Cacheable
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Transient
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "email")
	private String email;

	@Type(type = "true_false")
	private Boolean active;

	@JsonInclude(value = Include.NON_NULL)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> addresses;

	// bidirectional one to many
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Car> cars;

	// unidirectional one to many
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "user_id")
	private Set<Home> homes;

	public User() {
		this(null, 0, null);
	}

	public User(String name, int age, String email) {
		this(0, name, age, email);
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Logger getLog() {
		return log;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Home> getHomes() {
		return homes;
	}

	public void setHomes(Set<Home> homes) {
		this.homes = homes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static User fromJson(String json) {
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
			return "{}";
		}
	}
}
