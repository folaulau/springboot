package com.kaveinga.facebook.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Value("${server.auth-logout-uri}")
	private String oAuthLogoutUrl;
	
	RestTemplate rest = new RestTemplate();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("log out success");
		//String logoutResult = rest.getForObject(oAuthLogoutUrl, String.class);
		//System.out.println("logoutResult: "+logoutResult);
		
		System.out.println("log out OAuth2, url: "+oAuthLogoutUrl);
		response.sendRedirect(oAuthLogoutUrl);
		super.onLogoutSuccess(request, response, authentication);
	}
}
