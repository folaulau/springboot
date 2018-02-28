package com.folaukaveinga.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.folaukaveinga.api.security.AuthFilter;
import com.folaukaveinga.api.security.LoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthFilter authFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
		  		.anonymous()
			.and()
				.servletApi()
			.and()
				.headers()
			.and()
				.authorizeRequests()
		    			.antMatchers("/api/login/**").permitAll()
		    			.anyRequest().authenticated()
		    .and()
		    		.addFilterBefore(new LoginFilter(new AntPathRequestMatcher("/api/login")), UsernamePasswordAuthenticationFilter.class)
		    		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("username").password("test");
	}
}
