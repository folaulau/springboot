package com.folaukaveinga.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.folaukaveinga.security.ApiTokenSession;

public class SessionUserUtil {

	public static ApiTokenSession getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			ApiTokenSession session = (ApiTokenSession) auth.getPrincipal();
			return session;
		}
		return null;
	}

	public static void updateSessionUser(ApiTokenSession apiTokenSession) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Authentication updateAuth = new UsernamePasswordAuthenticationToken(apiTokenSession, auth.getCredentials(),
				auth.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(updateAuth);
	}

	public static boolean isAdmin(Authentication auth) {
		if (auth.getAuthorities().stream()
				.anyMatch(role -> role != null && ("ROLE_ADMIN".equals(role.getAuthority())
						|| "ROLE_OPERATIONS".equals(role.getAuthority()) || "ROLE_MARKETING".equals(role.getAuthority())
						|| "ROLE_SALES".equals(role.getAuthority()) || "ROLE_CLAIMS".equals(role.getAuthority())))) {
			return true;
		}
		return false;
	}

}
