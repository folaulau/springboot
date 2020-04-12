package com.folaukaveinga.multidbs.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public interface ApiSessionUtils {

	static Logger log = LoggerFactory.getLogger(ApiSessionUtils.class);

	/**
	 * save sidecarBrokerApiSession as principal for duration of rest call
	 * 
	 * @param sidecarBrokerApiSession
	 */
	public static void setSessionToken(UserSession userSession) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		if (userSession.getRoles() != null || userSession.getRoles().isEmpty() == false) {
			for (String role : userSession.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
			}
		}

		Authentication updateAuth = new UsernamePasswordAuthenticationToken(userSession,
				userSession.getMember().getId(), authorities);
		SecurityContextHolder.getContext().setAuthentication(updateAuth);
	}

	public static UserSession getApiSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			try {
				UserSession session = (UserSession) auth.getPrincipal();
				return session;
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}
		return null;
	}

	public static String getCurrentUserDatabase() {
		UserSession userSession = getApiSession();

		try {
			if (userSession != null && userSession.getMember() != null && userSession.getMember().getDb() != null) {
				return userSession.getMember().getDb();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Random rand = new Random();
		if (rand.nextInt(10) % 2 == 0) {
			return "springboot_clienta";
		} else {
			return "springboot_clientb";
		}
	}

}
