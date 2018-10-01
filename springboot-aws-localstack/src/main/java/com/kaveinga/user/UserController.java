package com.kaveinga.user;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "users", produces = "Rest API for User operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService    userService;
    
	@ApiOperation(value = "Save User")
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		log.info("resetPassword()", user.toJson());
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
