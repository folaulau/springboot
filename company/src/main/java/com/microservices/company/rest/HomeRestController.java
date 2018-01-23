package com.microservices.company.rest;

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

import com.microservices.company.domain.Pay;
import com.microservices.company.respository.PayrollClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
public class HomeRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private EurekaClient eurekaClient;
//	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private PayrollClient payrollClient;
	
	@RequestMapping(value={"/home","/home/"}, method = { RequestMethod.GET })
	public String home(Model model) {
		log.info("home rest page");
		Pay pay = payrollClient.getPay();
		log.info("Payroll status: {}",pay.toString());
		return pay.toString();
	}
}
