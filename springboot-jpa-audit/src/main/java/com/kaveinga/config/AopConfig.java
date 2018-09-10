package com.kaveinga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.kaveinga.audit.UserAop;

@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

	@Bean
	public UserAop userAop() {
		return new UserAop();
	}
}
