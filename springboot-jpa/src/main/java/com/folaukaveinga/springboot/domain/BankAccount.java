package com.folaukaveinga.springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
@Entity
@DiscriminatorValue(value="ach")
public class BankAccount extends PaymentMethod implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "account_holder_name")
	private String name;
	
	@Column(name = "bank")
	private String bank;
	
	@Column(name = "last4")
	private String last4;

	public BankAccount() {
		this(null,null,null);
	}

	public BankAccount(String name, String bank, String last4) {
		super();
		this.name = name;
		this.bank = bank;
		this.last4 = last4;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BankAccount [name=" + name + ", bank=" + bank + ", last4=" + last4 + "]";
	}

}
