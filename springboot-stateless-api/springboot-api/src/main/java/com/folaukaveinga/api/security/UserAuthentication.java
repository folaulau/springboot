package com.folaukaveinga.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.folaukaveinga.api.model.User;

@SuppressWarnings("serial")
public class UserAuthentication implements Authentication{
	
	private User user;
	private boolean authenticated;
	private String ipAddress;
	private String token;
	private List<String> auths;
	
	public UserAuthentication() {
		this(false);
	}
	
	public UserAuthentication(boolean authenticated) {
		this(null,authenticated,null,null);
	}
	
	public UserAuthentication(User user, boolean authenticated, String ipAddress, String token) {
		super();
		this.user = user;
		this.authenticated = authenticated;
		this.ipAddress = ipAddress;
		this.token = token;
		this.auths = new ArrayList<>();
		
		// custom
		setUser(new User(5,"Folau","folaukavienga@gmial.com","2323423423","test","active"));
		addAuth("USER");
		setAuthenticated(true);
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		 //grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
		 for(String auth:auths) {
			 grantedAuthorities.add(new SimpleGrantedAuthority(auth));
		 }
		return grantedAuthorities;
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public User getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getEmail();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		authenticated = isAuthenticated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getAuths() {
		return auths;
	}

	public void setAuths(List<String> auths) {
		this.auths = auths;
	}
	
	public void addAuth(String auth) {
		if(this.auths==null) {
			this.auths = new ArrayList<>();
		}
		this.auths.add(auth);
	}

	@Override
	public String toString() {
		return "UserAuthentication [user=" + user + ", authenticated=" + authenticated + ", ipAddress=" + ipAddress
				+ ", token=" + token + ", auths=" + auths + "]";
	}
	
	

}
