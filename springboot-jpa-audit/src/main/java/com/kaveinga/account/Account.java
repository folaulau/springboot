package com.kaveinga.account;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaveinga.user.User;
import com.kaveinga.utility.ObjectUtils;

import net.minidev.json.annotate.JsonIgnore;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer"})// "handler" and "hibernateLazyInitializer" are shown on user payload
@EntityListeners(AccountListener.class)
@Table(name="account")
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="balance")
	private int balance;
	
	@JsonIgnoreProperties(value = {"account"})
    @OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, mappedBy = "account")
    private Set<User> users;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", updatable = true)
	private Date updatedAt;
	
	public Account() {
		this(null);
	}
	
	public Account(Long id) {
		this(id,0,null);
	}

	public Account(Long id, int balance, Set<User> users) {
		super();
		this.id = id;
		this.balance = balance;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void addUser(User user) {
		if(this.users==null) {
			this.users = new HashSet<>();
		}
		this.users.add(user);
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String toJson() {
		try {
			return ObjectUtils.getObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			System.out.println("JsonProcessingException, msg: " + e.getLocalizedMessage());
			return "{}";
		}
	}

}
