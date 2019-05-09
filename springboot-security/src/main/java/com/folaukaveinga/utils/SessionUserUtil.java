package com.folaukaveinga.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.folaukaveinga.jwt.JwtPayload;
import com.folaukaveinga.security.ApiTokenSession;

public class SessionUserUtil {

	public static JwtPayload getJwtPayload() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			JwtPayload session = (JwtPayload) auth.getPrincipal();
			return session;
		}
		return null;
	}

	public static void updateSessionUser(JwtPayload jwtPayload) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Authentication updateAuth = new UsernamePasswordAuthenticationToken(jwtPayload, auth.getCredentials(),
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
