package com.folaukaveinga.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.dto.SessionDTO;
import com.folaukaveinga.jwt.JwtPayload;
import com.folaukaveinga.jwt.JwtTokenUtils;
import com.folaukaveinga.user.User;
import com.folaukaveinga.user.UserService;
import com.folaukaveinga.utils.HttpUtils;
import com.folaukaveinga.utils.ObjectUtils;
import com.folaukaveinga.utils.RandomGeneratorUtils;



/**
 * CustomUsernamePassworAuthenticationFilter
 * @author fkaveinga
 *
 */
public class CustomLoginFilter extends AbstractAuthenticationProcessingFilter {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Map<String,String> authenticationDetails = new HashMap<>();

	@Autowired
	private UserService userService;
	
	public CustomLoginFilter(String loginUrl, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(loginUrl));
		setAuthenticationManager(authManager);
	}

	
	/**
	 * Attemp Authentication process. Pass username and password to authentication provider
	 * @author fkaveinga
	 * @return Authentication
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String authorizationHeader = request.getHeader("authorization");
		log.info("Login Authorization Header: {}",authorizationHeader);
		
		if(authorizationHeader==null) {
			throw new InsufficientAuthenticationException("Authorization Header is null");
		}
		
		String email = getUsername(authorizationHeader);
		String password = getPassword(authorizationHeader);
		log.debug("email: {}",email);
		log.debug("password: {}",password);
		
		if(email == null || email.isEmpty()) {
			log.info("username is null");
			throw new InsufficientAuthenticationException("Username is null");
		}
		
		if(password == null || password.isEmpty()) {
			log.info("password is null");
			throw new InsufficientAuthenticationException("Password is null");
		}
		
		authenticationDetails.put("test", "good");
		
		return authenticateWithPassword(email, password);
		
	}
	
	private Authentication authenticateWithPassword(String email, String password) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		usernamePasswordAuthenticationToken.setDetails(authenticationDetails);
		return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
	}
	

	/**
	 * Write response when request was successful.
	 * @author fkaveinga
	 * @return HttpServletResponse
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.debug("successfulAuthentication(...)");
		String clientIpAddress = HttpUtils.getRequestIP(request);
		String clientUserAgent = HttpUtils.getRequestUserAgent(request);
		
		String email = authResult.getPrincipal().toString();
		
		User user = this.userService.getByEmail(email);
		
		JwtPayload jwtpayload = new JwtPayload(user, RandomGeneratorUtils.getUuid());
		jwtpayload.setDeviceId(clientUserAgent);
		
		String jwtToken = JwtTokenUtils.generateToken(jwtpayload);
		
		SessionDTO sessionDto = new SessionDTO();
		sessionDto.setEmail(email);
		sessionDto.setName(user.getName());
		sessionDto.setUserUid(user.getUid());
		sessionDto.setToken(jwtToken);
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		ObjectUtils.getObjectMapper().writeValue(response.getWriter(),sessionDto);
	}

	/**
	 * Write response when request was unsuccessful.
	 * @author fkaveinga
	 * @return HttpServletResponse
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.debug("unsuccessfulAuthentication(...)");
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		
		String message = failed.getLocalizedMessage();
		log.debug("Error message: {}",message);

		response.setStatus(HttpStatus.BAD_REQUEST.value());
		
		ObjectNode result = ObjectUtils.getObjectNode();
		
		result.put("status", "invalid email or password");
		
		ObjectUtils.getObjectMapper().writeValue(response.getWriter(), result);
	}
	
	/**
	 * Parse token for username
	 * @param authorizationHeader
	 * @return String username
	 */
	private String getUsername(String authorizationHeader) {
		log.debug("getUsername(..)");
		String username = null;
		try {
			String usernamePasswordToken = StringUtils.substringAfter(authorizationHeader, " ").trim();
			//log.info("usernamePasswordToken: {}",usernamePasswordToken);
			String rawToken = this.decodeBase64Token(usernamePasswordToken);
			log.debug("rawToken: {}",rawToken);
			username  = StringUtils.substringBefore(rawToken, ":");
			log.debug("username: {}",username);
			return username;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}
	
	/**
	 * Parse token for password
	 * @param authorizationHeader
	 * @return String password
	 */
	private String getPassword(String authorizationHeader) {
		log.debug("getPassword(..)");
		String password = null;
		try {
			String usernamePasswordToken = StringUtils.substringAfter(authorizationHeader, " ").trim();

			String rawToken = this.decodeBase64Token(usernamePasswordToken);
			log.debug("rawToken: {}",rawToken);
			password  = StringUtils.substringAfter(rawToken, ":");
			log.debug("username: {}",password);
			return password;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return password;
		
	}
	
	
	/**
	 * Decode authentication token
	 * @param usernamePasswordToken
	 * @return String
	 */
	private String decodeBase64Token(String usernamePasswordToken) {
		byte[] decodedBytes = Base64.getDecoder().decode(usernamePasswordToken);
		return new String(decodedBytes);
	}

}
