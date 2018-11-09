package com.folaukaveinga.springboot.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@EnableJpaAuditing
@Configuration
public class JPAConfig {

//	@Bean
//	public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
//			EntityManagerFactoryBuilder builder) {
//		return builder
//				.dataSource(orderDataSource())
//				.packages(Order.class)
//				.persistenceUnit("orders")
//				.build();
//	}
	
}
