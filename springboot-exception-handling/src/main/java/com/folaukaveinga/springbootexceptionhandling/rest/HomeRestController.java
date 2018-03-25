package com.folaukaveinga.springbootexceptionhandling.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeRestController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping({"/api","/api/"})
	public ResponseEntity<?> home(){
		log.info("Hello from rest home!");
		return new ResponseEntity<>("Hello", HttpStatus.OK);
	}
	
	@GetMapping({"/api/throw-400/","/api/throw-400"})
	public ResponseEntity<?> throw400() throws RuntimeException{
		log.info("Hello throw400!");
		throw new RuntimeException("custom 400 message");
	}
}
