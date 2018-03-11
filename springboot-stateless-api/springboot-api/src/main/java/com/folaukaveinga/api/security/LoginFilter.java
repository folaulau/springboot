package com.folaukaveinga.api.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.api.cache.UserCache;
import com.folaukaveinga.api.dao.UserDAO;
import com.folaukaveinga.api.model.User;
import com.folaukaveinga.api.utility.PasswordUtils;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserCache userCache;

	@Autowired
	private UserDAO userDAO;

	public LoginFilter(AntPathRequestMatcher defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(authenticationManager);
	}

	@SuppressWarnings("unused")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("authenticate login");
		UsernamePasswordAuthenticationToken authenticationToken = null;
		
		User loginUser = retrieveLoginCredentials(request);
		
		log.info("username: {}",loginUser.getEmail());
		log.info("password: {}",loginUser.getPassword());
		
		HashMap<String, Object> status = new HashMap<>();

		if (loginUser == null) {
			status.put("status", "credentials-not-found");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			
			ObjectMapper mapper = new ObjectMapper();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(mapper.writeValueAsString(status));
			log.error("authentication credentials not found");
			throw new AuthenticationCredentialsNotFoundException("authentication credentials not found");
		} else {
			User user = userDAO.getUserByEmail(loginUser.getEmail());
			log.info(user.toString());
			if (PasswordUtils.verify(loginUser.getPassword(), user.getPassword())) {

				final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				for (String role : user.getRoles()) {
					grantedAuthorities.add(new SimpleGrantedAuthority(role));
				}

				if (grantedAuthorities.isEmpty()) {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					ObjectMapper mapper = new ObjectMapper();
					response.setCharacterEncoding("UTF-8");
					status.put("status", "credentials-not-found");
					response.getWriter().write(mapper.writeValueAsString(status));
					log.error("insufficient authentication authority");
					throw new InsufficientAuthenticationException("user has no authority");
				}

				// authentication and authorization success
				authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword(), grantedAuthorities);

				log.info("isAuthenticated: {}", authenticationToken.isAuthenticated());
				log.info("authorities: {}", authenticationToken.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
				String jwtToken = jwtTokenUtil.generateToken(user);
				log.info("jwtToken: {}", jwtToken);
				
				// cache token
				userCache.add(jwtToken, user);
				
				response.setStatus(HttpStatus.OK.value());
				
				ObjectMapper mapper = new ObjectMapper();
				response.setCharacterEncoding("UTF-8");
				status.put(AUTH_HEADER_NAME, jwtToken);
				status.put("status", "authenticated");
				response.getWriter().write(mapper.writeValueAsString(status));
				
				return authenticationToken;
			} else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				
				ObjectMapper mapper = new ObjectMapper();
				response.setCharacterEncoding("UTF-8");
				status.put("status", "credentials-not-found");
				response.getWriter().write(mapper.writeValueAsString(status));
				log.error("bad credentials");
				throw new BadCredentialsException("password is invalid");
			}
		}
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

	private User retrieveLoginCredentials(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		log.info(sb.toString());
		JSONObject jObj;
		User user = new User();
		try {
			jObj = new JSONObject(sb.toString());
			String username = jObj.getString("username");
			String password = jObj.getString("password");
			
			user.setEmail(username);
			user.setPassword(password);
			
			return user;
		} catch (JSONException e) {
			log.error("JSONException: {}", e.getLocalizedMessage());
		}

		return null;
	}

}
