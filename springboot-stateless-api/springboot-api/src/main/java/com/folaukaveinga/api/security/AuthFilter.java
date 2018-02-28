package com.folaukaveinga.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.api.model.User;
@Service
public class AuthFilter extends GenericFilterBean{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		log.info("filter auth");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		final String token = httpRequest.getHeader(AUTH_HEADER_NAME);
		log.info("token: {}", token);
		log.info("httpRequest: {}", httpRequest);
		log.info("httpResponse: {}", httpResponse);
		tokenAuthenticationService.getAuthentication(httpRequest, httpResponse);
		
		UserAuthentication userAuthenticatioin = new UserAuthentication(true);
		
		log.info(userAuthenticatioin.toString());
		
		SecurityContextHolder.getContext().setAuthentication(userAuthenticatioin);
		
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		filterChain.doFilter(request, response);
		
		
		
//		if(auth.isAuthenticated()) {
//			filterChain.doFilter(request, response);
//		}else {
//			response.setCharacterEncoding("UTF-8");
//			httpResponse.getWriter().write("Not authorized");
//		}
	}

}
