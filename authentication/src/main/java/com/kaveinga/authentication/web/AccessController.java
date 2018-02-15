package com.kaveinga.authentication.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value= {"/login","/login/"})
	public String login(HttpServletRequest request) {
		String referrer = request.getHeader(HttpHeaders.REFERER);
		log.info("display login page, referrer: {}, ssn: {}", referrer, request.getSession().getId());
		return "login";
//		if(referrer!=null && referrer.isEmpty()==false) {
//			return "login";
//		}else {
//			return "nologin";
//		}
	}
}
