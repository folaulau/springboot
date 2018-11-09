package com.folaukaveinga.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.springboot.utility.Factory;

@Configuration
public class SpringConfig {
	
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		
//		multipartResolver.setMaxUploadSize(100000);
//		return new CommonsMultipartResolver();
//	}
	
	
	@Bean
	public ObjectMapper objectMapper() {
		return Factory.getObjectMapper();
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
}
