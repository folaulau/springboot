package com.folaukaveinga.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.dto.SessionDTO;
import com.folaukaveinga.dto.SignupRequest;
import com.folaukaveinga.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Controller")
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Sign Up")
	@PostMapping("/signup")
	public ResponseEntity<SessionDTO> signUp(@ApiParam(name="user", required=true, value="user") @Valid @RequestBody SignupRequest signupRequest){
		log.debug("signUp(..)");
		SessionDTO userSession = userService.signUp(signupRequest);
		log.debug("userSession: {}",ObjectUtils.toJson(userSession));
		return new ResponseEntity<>(userSession, HttpStatus.OK);
	}
	
	/**
	 * This method is for show only. It does not get called on login.
	 * Check CustomLoginFilter.java - Spring security set this up.
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Login")
	@PostMapping("/login")
	public ResponseEntity<SessionDTO> login(@RequestHeader("x-api-key") String apiKey,@ApiParam(name="authorization", required=true, value="Base64 username and password encoded token") @RequestHeader("authorization") String authorization,
			@ApiParam(name = "type", required = true, value = "type [<i>password, admin, finger-print</i>]") @RequestParam("type") String type){
		log.info("login(...)");
		return new ResponseEntity<>(new SessionDTO(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Logout")
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("token") String token){
		log.info("logout(...)");
		
		ObjectNode result = ObjectUtils.getObjectNode();
		result.put("status", "good");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
