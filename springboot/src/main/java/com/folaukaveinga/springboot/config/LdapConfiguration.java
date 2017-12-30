package com.folaukaveinga.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Autowired
    Environment env;

    @Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl("ldap://localhost:8389/");
        contextSource.setBase("dc=springframework,dc=org");
        contextSource.setUserDn("uid=ben");
        contextSource.setPassword("benspassword");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());        
    }

}