package com.folaukaveinga.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.api.model.Status;
import com.folaukaveinga.api.model.User;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<Status> home(){
		log.info("hitting public api");
		return new ResponseEntity<>(new Status("good","Hello from our public api"), HttpStatus.OK);
	}
}
