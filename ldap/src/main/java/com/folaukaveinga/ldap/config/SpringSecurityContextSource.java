package com.folaukaveinga.ldap.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.LdapUtils;
import org.springframework.security.ldap.authentication.SpringSecurityAuthenticationSource;

@Configuration
public class SpringSecurityContextSource {
	@Value("${spring.ldap.urls}")
	private String LDAP_URL;
	

	@Value("${spring.ldap.base}")
	private String LDAP_BASE;
	
	@Value("${spring.ldap.cn}")
	private String LDAP_CN;
	
	@Value("${spring.ldap.password}")
	private String LDAP_PASSWORD;
	
	@Profile("dev")
	@Bean
	public DefaultSpringSecurityContextSource devSpringSecurityContextSource() {
		DefaultSpringSecurityContextSource contextSource =  new DefaultSpringSecurityContextSource(Arrays.asList(LDAP_URL),LDAP_BASE);
		contextSource.setPassword(LDAP_PASSWORD);
		contextSource.setUserDn(LDAP_CN+","+LDAP_BASE);
		return contextSource;
	}
	
	@Profile("prod")
	@Bean
	public DefaultSpringSecurityContextSource homeSpringSecurityContextSource() {
		DefaultSpringSecurityContextSource contextSource = 
				new DefaultSpringSecurityContextSource(Arrays.asList(LDAP_URL),LDAP_BASE);
		contextSource.setPassword(LDAP_PASSWORD);
		contextSource.setUserDn(LDAP_CN+","+LDAP_BASE);
		return contextSource;
	}

}