package com.folaukaveinga.ldap.config;

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
public class LdapTemplateConfiguration {

	@Autowired
	private DefaultSpringSecurityContextSource springSecurityContextSource;
	

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(springSecurityContextSource);
	}

}