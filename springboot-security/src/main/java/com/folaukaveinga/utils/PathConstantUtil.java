package com.folaukaveinga.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Constants for paths
 * 
 * @author fkaveinga
 * @see ObjectUtil
 * @see ObjectMapper
 */
public final class PathConstantUtil {
	private PathConstantUtil() {}

	public static final String LOGIN_URL ="/login";
	public static final String LOGOUT_URL ="/logout";
	public static final String SIGNUP_URL ="/signup";
	public static final String PING_URL ="/ping";
	public static final String AUTH_TOKEN_URL ="/generate-basic-auth-token";
	
	public static final String[] SWAGGER_DOC_URLS = {
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/v2/api-docs",
            "/webjars/**",
            "/webjars/springfox-swagger-ui/**",
            "/webjars/springfox-swagger-ui/springfox.css?v=2.8.0-SNAPSHOT",
            "/webjars/springfox-swagger-ui/swagger-ui.css?v=2.8.0-SNAPSHOT"
    };
	
	
}
