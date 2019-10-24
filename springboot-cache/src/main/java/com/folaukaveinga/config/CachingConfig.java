package com.folaukaveinga.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig{
	
	
    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
	
	private class CustomKeyGenerator implements KeyGenerator {
		  
	    public Object generate(Object target, Method method, Object... params) {
	    	StringBuilder strParams = new StringBuilder(target.getClass().getSimpleName());
	    	strParams.append("_");
			strParams.append(method.getName());
	    	for(Object param: params) {
				strParams.append("_");
				strParams.append(param);
			}
	    	System.out.println("key="+strParams.toString());
	        return strParams.toString();
	    }
	}

}
