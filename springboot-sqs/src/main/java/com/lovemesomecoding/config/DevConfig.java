package com.lovemesomecoding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

@Profile(value = {"dev"})
@Configuration
public class DevConfig {
    private Logger                 log = LoggerFactory.getLogger(this.getClass());
    
	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		
		return DefaultAWSCredentialsProviderChain.getInstance();
	}
   
}