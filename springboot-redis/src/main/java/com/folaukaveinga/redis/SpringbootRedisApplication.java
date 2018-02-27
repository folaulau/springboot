package com.folaukaveinga.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class SpringbootRedisApplication  implements CommandLineRunner{

	@Autowired
	private StringRedisTemplate template;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
