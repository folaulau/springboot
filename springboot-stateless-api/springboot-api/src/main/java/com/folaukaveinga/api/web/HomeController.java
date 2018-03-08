package com.folaukaveinga.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String home(){
		log.info("home");
		return "home";
	}
}
