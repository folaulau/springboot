package com.folaukaveinga.stateless.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.stateless.dao.UserDAO;
import com.folaukaveinga.stateless.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userService;
	
	
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, HttpServletResponse response){
		log.info("get all users");
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
