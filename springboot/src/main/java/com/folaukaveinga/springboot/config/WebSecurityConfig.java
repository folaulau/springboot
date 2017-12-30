package com.folaukaveinga.springboot.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@EnableGlobalMethodSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DefaultSpringSecurityContextSource defaultSpringSecurityContextSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
	        .and()
	        		.authorizeRequests()
	        		.anyRequest().fullyAuthenticated()
	        .and()
	        		.formLogin()
	        .and()
		        .logout();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
	        .userDnPatterns("uid={0},ou=people")
	        .groupSearchBase("ou=groups")
	        .contextSource(defaultSpringSecurityContextSource)
	        .passwordCompare()
	        .passwordEncoder(new LdapShaPasswordEncoder())
	        .passwordAttribute("userPassword");
	}
}