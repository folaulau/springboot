package com.folaukaveinga.multidbs.security;

import java.io.Serializable;
import java.util.Set;

import com.folaukaveinga.multidbs.member.Member;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	private Member member;
	private Set<String> roles;
	private String database;

	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "UserSession [token=" + token + ", member=" + member + ", roles=" + roles + ", database=" + database
				+ "]";
	}

}
