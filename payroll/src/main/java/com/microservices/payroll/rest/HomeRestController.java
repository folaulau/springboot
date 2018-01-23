package com.microservices.payroll.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.payroll.domain.Pay;
import com.microservices.payroll.respository.Payment;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
public class HomeRestController implements Payment {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	@RequestMapping(value={"/api/pay", "/api/pay/"})
	public Pay getPay() {
		log.info("get pay");
		return new Pay(1,"december");
	}
}
