package com.kaveinga.instagram;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

public class ServletInitializer extends SpringBootServletInitializer {
	
//	@Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InstagramApplication.class);
	}

}
