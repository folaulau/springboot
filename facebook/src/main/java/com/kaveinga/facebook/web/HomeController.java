package com.kaveinga.facebook.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HttpSession ssn;
	
	@GetMapping(value={"","/"})
	public String home(Model model) {
		log.info("home page");
		return "index";
	}
	
	@GetMapping(value={"/secure","/secure/"})
	public String secure(Model model, HttpServletRequest request) {
		log.info("secure page, ssn id: {}", ssn.getId());
		try {
			Principal principal = request.getUserPrincipal();
			log.info("principal name: " + principal.getName());
			log.info(principal.toString());
		} catch (Exception e) {
			log.warn("Principal exception, msg: {}",e.getMessage());
		}
		return "secure";
	}
	
	@GetMapping(value={"/access-denied","/access-denied/"})
	public String accessDeny(Model model) {
		log.info("access denied page");
		return "access_denied";
	}
}
