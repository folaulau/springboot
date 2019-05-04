package com.folaukaveinga.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthenticationFilter extends OncePerRequestFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handle token missing error <br>
	 * Handle cached user not found error <br>
	 * Handle user with no roles <br>
	 * If all goes well, let request continue down the line
	 * 
	 * @author fkaveinga
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.debug("doFilterInternal(...)");
		String token = request.getHeader("token");
		log.debug("Token: {}", token);

		ApiTokenSession apiTokenSession = null;

		Authentication authentication = getAuthentication(request, apiTokenSession);

		// log.info(authentication.getCredentials().toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		filterChain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,
			ApiTokenSession apiTokenSession) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (apiTokenSession.getUserAuthorities() != null
				|| apiTokenSession.getUserAuthorities().isEmpty() == false) {
			for (String role : apiTokenSession.getUserAuthorities()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
			}
		}
		return new UsernamePasswordAuthenticationToken(apiTokenSession.getUserEmail(),
				apiTokenSession.getUserEmail(), authorities);
	}
}
