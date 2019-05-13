package com.lovemesomecoding.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lovemesomecoding.utils.HttpUtils;
import com.lovemesomecoding.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Controller")
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Sign Up")
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@ApiParam(name="user", required=true, value="user") @Valid @RequestBody User user){
		log.debug("signUp(..)");
		User userSession = userService.create(user);
		log.debug("userSession: {}",ObjectUtils.toJson(userSession));
		return new ResponseEntity<>(userSession, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping("/users")
	public ResponseEntity<User> update(@ApiParam(name="user", required=true, value="user") @Valid @RequestBody User user){
		log.debug("update(..)");
		User updatedUser = userService.update(user);
		log.debug("updatedUser: {}",ObjectUtils.toJson(updatedUser));
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
}
