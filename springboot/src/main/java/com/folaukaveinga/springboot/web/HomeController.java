package com.folaukaveinga.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/",""}, method = { RequestMethod.GET })
	public String home(Model model) {
		model.addAttribute("name", "Folau");
		log.info("home");
		return "index";
	}
}
