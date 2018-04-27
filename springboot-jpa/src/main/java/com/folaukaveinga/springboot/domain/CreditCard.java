package com.folaukaveinga.springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
@Entity
@DiscriminatorValue(value="credit_card")
public class CreditCard extends PaymentMethod implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "last4")
	private String last4;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "name")
	private String name;

	public CreditCard() {
		this(null,null,null);
	}
	
	public CreditCard(String last4, String brand, String name) {
		super();
		this.last4 = last4;
		this.brand = brand;
		this.name = name;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CreditCard [last4=" + last4 + ", brand=" + brand + ", name=" + name + "]";
	}
}
