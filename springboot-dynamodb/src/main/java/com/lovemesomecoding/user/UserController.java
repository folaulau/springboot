package com.lovemesomecoding.user;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users", produces = "Rest API for user operations", tags = "User Controller")
@RequestMapping("/users")
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Sign Up")
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@ApiParam(name = "user", required = true, value = "user") @Valid @RequestBody User user) {
		log.debug("signUp(..)");
		return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Users")
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		log.debug("signUp(..)");
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	
}
