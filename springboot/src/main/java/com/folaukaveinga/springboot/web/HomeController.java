package com.folaukaveinga.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.folaukaveinga.springboot.domain.User;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private User user;
	
	@RequestMapping(value={"/",""}, method = { RequestMethod.GET })
	public String home(Model model) {
		model.addAttribute("name", user.getName());
		log.info("home");
		return "index";
	}
}
