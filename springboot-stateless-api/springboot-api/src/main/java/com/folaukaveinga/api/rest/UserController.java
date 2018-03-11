package com.folaukaveinga.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.api.dao.UserDAO;
import com.folaukaveinga.api.model.Status;
import com.folaukaveinga.api.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userService;
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<Status> getUser(HttpServletRequest request, HttpServletResponse response){
		log.info("get user required role USER");
		return new ResponseEntity<>(new Status("good","Hello from role USER"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value={"/admin","/admin/"}, method=RequestMethod.GET)
	public ResponseEntity<Status> admin(){
		log.info("get user required role ADMIN");
		return new ResponseEntity<>(new Status("good","Hello from role ADMIN"), HttpStatus.OK);
	}
}
