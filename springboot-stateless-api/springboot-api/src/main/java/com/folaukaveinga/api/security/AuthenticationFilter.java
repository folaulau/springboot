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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.api.model.User;
@Service
public class AuthenticationFilter extends GenericFilterBean{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		log.info("filter auth");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		log.info("request url: {}",httpRequest.getRequestURL());
		
		Authentication authentication = tokenAuthenticationService.getAuthentication(httpRequest, httpResponse);
		
		log.info(authentication.toString());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		if(authentication.isAuthenticated()) {
			log.info("request is authenticated");
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
		}else {
			log.info("Not authorized");
			response.setCharacterEncoding("UTF-8");
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			httpResponse.getWriter().write("Not authorized");
		}
	}

}
