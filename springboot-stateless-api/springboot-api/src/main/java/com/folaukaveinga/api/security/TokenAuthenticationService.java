package com.folaukaveinga.api.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.folaukaveinga.api.model.User;
@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenHandlerService tokenHandlerService;
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		log.info("token: {}", token);
		UserAuthentication auth = new UserAuthentication();
		
		if(token!=null) {
			User user = null;
			try {
				user = tokenHandlerService.parseUserFromToken(token);
				response.setStatus(HttpServletResponse.SC_OK);
				auth.setUser(user);
				auth.setAuthenticated(true);
				auth.setToken(token);
				log.info("token is valid");
				return auth;
			} catch (Exception e) {
				log.warn("Exception, msg: {}",e.getMessage());
			}
			
		}
		log.info("token is invalid");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		auth.setAuthenticated(false);
		return auth;
	}
}
