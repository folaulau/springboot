package com.folaukaveinga.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.api.cache.UserCache;
import com.folaukaveinga.api.model.User;
@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserCache userCache;
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		log.info("token: {}", token);
		PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken("","");
		authenticationToken.setAuthenticated(false);
		
		if(token!=null) {
			try {
				User user = userCache.findUser(token);
				log.info(user.toString());
				if(user!=null) {
					
					final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
					for (String role : user.getRoles()) {
						grantedAuthorities.add(new SimpleGrantedAuthority(role));
					}
					
					authenticationToken = new PreAuthenticatedAuthenticationToken(user.getEmail(), user.getPassword(), grantedAuthorities);
					
					if (grantedAuthorities.isEmpty()) {
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						//authenticationToken.setAuthenticated(false);
					}else {
						response.setStatus(HttpServletResponse.SC_OK);
						//authenticationToken.setAuthenticated(true);
					}
					
					return authenticationToken;
				}
				
				
			} catch (Exception e) {
				log.warn("Exception, msg: {}",e.getMessage());
				
			}
			
		}
		
		
		log.info("token is invalid");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		authenticationToken.setAuthenticated(false);
		return authenticationToken;
	}
}
