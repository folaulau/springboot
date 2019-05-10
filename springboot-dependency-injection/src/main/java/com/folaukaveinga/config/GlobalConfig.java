package com.folaukaveinga.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.folaukaveinga.service.OrderService;
import com.folaukaveinga.service.OrderServiceImp;

@Configuration
public class GlobalConfig {

	@Bean
	public OrderService getOrderService() {
		OrderService orderService = new OrderServiceImp();
		
		return orderService;
	}
}
