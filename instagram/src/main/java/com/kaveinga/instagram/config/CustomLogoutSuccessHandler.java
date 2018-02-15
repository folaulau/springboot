package com.kaveinga.instagram.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${server.auth-logout-uri}")
	private String oAuthLogoutUrl;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("log out success");
		log.info("log out OAuth2, url: "+oAuthLogoutUrl);
		response.sendRedirect(oAuthLogoutUrl);
		super.onLogoutSuccess(request, response, authentication);
	}
}
