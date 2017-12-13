package com.kaveinga.authentication.config;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("log out success");
		//String logoutResult = rest.getForObject(oAuthLogoutUrl, String.class);
		//System.out.println("logoutResult: "+logoutResult);
		String referrer = request.getHeader(HttpHeaders.REFERER);
		String from = request.getHeader(HttpHeaders.FROM);
		URL url = new URL(referrer);
		String host = url.getHost();
		int port = url.getPort();
		String protocol = url.getProtocol();
		System.out.println("referrer: "+referrer);
		System.out.println("host: "+host);
		System.out.println("protocol: "+protocol);
		System.out.println("port: "+port);
		String redirectUrl = protocol+"://"+host+":"+port+"/ui";
		System.out.println("redirect to: "+redirectUrl);
		response.sendRedirect(redirectUrl);
		super.onLogoutSuccess(request, response, authentication);
	}
}
