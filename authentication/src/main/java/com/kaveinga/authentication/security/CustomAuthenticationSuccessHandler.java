package com.kaveinga.authentication.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {
		log.info("logged in, email= {}, ssn: {}",authentication.getName(),request.getSession().getId());
		//final String email = authentication.getName();
		
		String referrer = response.getHeader(HttpHeaders.REFERER);
		log.info("referrer: {}",referrer);
		//this.getRedirectStrategy().sendRedirect(request, response,"http://localhost:8088");
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
