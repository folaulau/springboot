package com.folaukaveinga.springboot.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.LdapUtils;
import org.springframework.security.ldap.authentication.SpringSecurityAuthenticationSource;

@Configuration
public class LdapConfiguration {

	@Bean
	public DefaultSpringSecurityContextSource defaultSpringSecurityContextSource() {
		DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"),"dc=springframework,dc=org");
		//contextSource.setAuthenticationSource(springSecurityAuthenticationSource());
		return contextSource;
	}
	
//	@Bean
//	public SpringSecurityAuthenticationSource springSecurityAuthenticationSource() {
//		SpringSecurityAuthenticationSource authenticationSource = new SpringSecurityAuthenticationSource();
//		String password = authenticationSource.getCredentials();
//		String username = authenticationSource.getPrincipal();
//		System.out.println("username: "+username+", password: "+password);
//		return authenticationSource;
//	}

	// DefaultSpringSecurityContextSource extends LdapContextSource
	// @Bean
	// public LdapContextSource ldapContextSource() {
	// LdapContextSource contextSource= new LdapContextSource();
	// contextSource.setUrl("ldap://localhost:8389/");
	// contextSource.setBase("dc=springframework,dc=org");
	// return contextSource;
	// }

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(defaultSpringSecurityContextSource());
	}

}