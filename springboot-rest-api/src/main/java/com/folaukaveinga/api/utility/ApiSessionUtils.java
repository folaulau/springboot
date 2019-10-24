package com.folaukaveinga.api.utility;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.folaukaveinga.api.session.ApiSession;

public interface ApiSessionUtils {

	Logger log = LoggerFactory.getLogger(ApiSessionUtils.class);
	
	
	public static Authentication setSession() {
		
		Authentication auth = new UsernamePasswordAuthenticationToken(new ApiSession(),
				"test@gmail.com", null);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return auth;
	}
	
	public static Authentication setSession(ApiSession session) {
		
		Authentication auth = new UsernamePasswordAuthenticationToken(session,
				session.getEmail(), null);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}
	
	public static ApiSession getApiSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			try {
				ApiSession session = (ApiSession) auth.getPrincipal();
				return session;
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return null;
	}
	
	public static String getName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			
			
			try {
				ApiSession session = (ApiSession) auth.getPrincipal();
				return (session!=null) ? session.getName() : "Rest API";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	public static void setFingerPrint(String fingerPrint) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			log.info("Authentication is not null");
			
			try {
				ApiSession session = (ApiSession) auth.getPrincipal();
				session.setFingerPrint(fingerPrint);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			log.info("Authentication is null");
			auth = setSession();
			ApiSession session = (ApiSession) auth.getPrincipal();
			session.setFingerPrint(fingerPrint);
		}
	}

	public static String getFingerPrint() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			
			
			try {
				ApiSession session = (ApiSession) auth.getPrincipal();
				return (session!=null) ? session.getFingerPrint() : "Rest API";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
