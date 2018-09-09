package com.kaveinga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaveinga.utility.ObjectUtils;

@Configuration
public class GlobalConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		return ObjectUtils.getObjectMapper();
	}

}
