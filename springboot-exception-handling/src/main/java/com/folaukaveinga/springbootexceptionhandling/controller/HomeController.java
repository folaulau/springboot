package com.folaukaveinga.springbootexceptionhandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping({"","/"})
	public String home() {
		log.info("Hello from home!");
		return "home";
	}
}
