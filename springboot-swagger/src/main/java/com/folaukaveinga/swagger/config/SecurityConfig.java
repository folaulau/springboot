package com.folaukaveinga.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 private static final String[] AUTH_WHITELIST = {
	            // -- swagger ui
	            "/doc/v2/api-docs",
	            "/doc/swagger-resources",
	            "/doc/swagger-resources/**",
	            "/doc/configuration/ui",
	            "/doc/configuration/security",
	            "/doc/swagger-ui.html",
	            "/doc/webjars/**"
	            // other public endpoints of your API may be appended to this array
	    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    .csrf().disable()
	     .authorizeRequests()
	      .antMatchers(AUTH_WHITELIST).permitAll()
	      .anyRequest().authenticated()
	      .and()
	    .httpBasic().and()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}
}
