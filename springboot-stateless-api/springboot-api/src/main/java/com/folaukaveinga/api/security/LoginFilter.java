package com.folaukaveinga.api.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.folaukaveinga.api.cache.UserCache;
import com.folaukaveinga.api.model.User;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserCache userCache;

	public LoginFilter(AntPathRequestMatcher defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("authenticate login");
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		System.out.println(sb.toString());
		JSONObject jObj;
		String username = null;
		String password = null;
		try {
			jObj = new JSONObject(sb.toString());
			username = jObj.getString("username");
			password = jObj.getString("password");

			log.info("username: {}", username);
			log.info("password: {}", password);
		} catch (JSONException e) {
			log.error("JSONException: {}", e.getLocalizedMessage());
		}

		// authenticat

		UserAuthentication userAuthentication = new UserAuthentication(true);

		log.info(userAuthentication.toString());

		SecurityContextHolder.getContext().setAuthentication(userAuthentication);

		log.info("isAuthenticated: {}", userAuthentication.isAuthenticated());
		log.info("authorities: {}", userAuthentication.getAuthorities());
		String jwtToken = jwtTokenUtil.generateToken(username);
		log.info("jwtToken: {}", jwtToken);
		userCache.add(jwtToken, userAuthentication.getUser());
		
		response.setHeader(AUTH_HEADER_NAME, jwtToken);
		response.setStatus(HttpStatus.OK.value());
		return userAuthentication;
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.info("unsuccessful authentication");
		// super.unsuccessfulAuthentication(request, response, failed);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		log.info("successful authentication");
		// super.successfulAuthentication(request, response, chain, authentication);
	}

}
