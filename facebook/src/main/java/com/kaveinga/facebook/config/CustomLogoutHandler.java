package com.kaveinga.facebook.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security logout handler, specialized for Ajax requests.
 */
@Component
public class CustomLogoutHandler implements LogoutHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void logout(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) {
		log.info("loggin out...");
		log.info("auth type: {}", request.getAuthType());
		log.info("cookies");
		for (Cookie co : request.getCookies()) {
			log.info("cookie: {}, value: {}, age: {}", co.getName(), co.getValue(), co.getMaxAge());
		}
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			log.info("header name: ", names.nextElement());
		}

		Object details = authentication.getDetails();
		if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {
			String accessToken = ((OAuth2AuthenticationDetails)details).getTokenValue();
			log.info("accessToken: {}",accessToken);
			
		}

		
		authentication.setAuthenticated(false);
		request.getSession().invalidate();
		SecurityContextHolder.clearContext();
		try {
			response.sendRedirect("/ui/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);

		log.info("logged out successfully!!!");
		response.setStatus(HttpServletResponse.SC_OK);

	}

}
