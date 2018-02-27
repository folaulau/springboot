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
		log.info("home");
		return "index";
	}
	
	@RequestMapping(value={"/form-elements","/form-elements/"}, method = { RequestMethod.GET })
	public String formElements(Model model) {
		log.info("form elements");
		return "form_elements/index";
	}
	
	@RequestMapping(value={"/form-creator","/form-creator/"}, method = { RequestMethod.GET })
	public String showFormCreator(Model model) {
		log.info("form creator");
		return "form_creator/index";
	}
}
