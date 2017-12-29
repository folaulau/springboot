package com.folaukaveinga.springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_details")
public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Transient
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="region")
	private String region;
	
	@Column(name="employer")
	private String employer;

	public UserDetails() {
	}

	public UserDetails(long id, String region, String employer) {
		super();
		this.id = id;
		this.region = region;
		this.employer = employer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Logger getLog() {
		return log;
	}
	
	
}
