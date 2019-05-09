package com.folaukaveinga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.utils.ObjectUtils;

@Configuration
public class GlobalConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return ObjectUtils.getObjectMapper();
	}
}
