package com.folaukaveinga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.folaukaveinga.interceptor.GlobalInterceptor;

@Configuration
public class RestConfig implements WebMvcConfigurer {

	/**
	 * Disable CORS <br>
	 * - For testing, Allow all methods but ideally login should only allow POST to
	 * /login
	 * 
	 * @return WebMvcConfigurer
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}

	
	@Autowired
	private GlobalInterceptor globalInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor);
		//WebMvcConfigurer.super.addInterceptors(registry);
	}
}
