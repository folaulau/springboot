package com.folaukaveinga.api.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	public LoginFilter(AntPathRequestMatcher defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("authenticate login");
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			log.info("parameter: "+en.nextElement());
		}
		
		log.info("username: {}",username);
		log.info("password: {}",password);
		
		final String ubane = request.getHeader("username");
		final String pwd = request.getHeader("password");
		
		log.info("ubane: {}",ubane);
		log.info("pwd: {}",pwd);
		
		UserAuthentication userAuthentication = new UserAuthentication(true);
		
		log.info(userAuthentication.toString());
		
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		return userAuthentication;
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.info("unsuccessful authentication");
		super.unsuccessfulAuthentication(request, response, failed);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("successful authentication");
		//
		//response.setStatus(HttpServletResponse.SC_OK);
		response.addHeader(AUTH_HEADER_NAME, "asdfasdfkjalksdjflkasjdf");
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
