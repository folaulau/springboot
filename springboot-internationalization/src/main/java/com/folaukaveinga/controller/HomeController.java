package com.folaukaveinga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("")
	public String home() {
		log.info("hello...");
		return "hello";
	}
	
}
