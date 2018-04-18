package com.folaukaveinga.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/doc/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
		registry.addRedirectViewController("/doc/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/doc/swagger-resources/configuration/security","/swagger-resources/configuration/security");
		registry.addRedirectViewController("/doc/swagger-resources", "/swagger-resources");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/doc/**")
		.addResourceLocations("classpath:/META-INF/resources/");
	}
}
