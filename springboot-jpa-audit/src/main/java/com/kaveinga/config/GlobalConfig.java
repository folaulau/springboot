package com.kaveinga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaveinga.utility.ObjectUtils;

@Configuration
public class GlobalConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return ObjectUtils.getObjectMapper();
	}

}
