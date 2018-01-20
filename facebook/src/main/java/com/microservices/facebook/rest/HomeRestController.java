package com.microservices.facebook.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
//	@RequestMapping(value={"/info","/info/"}, method = { RequestMethod.GET })
//	public String home(Model model) {
//		log.info("home page");
//		return "home";
//	}
}
