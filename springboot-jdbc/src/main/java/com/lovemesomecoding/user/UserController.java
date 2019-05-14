package com.lovemesomecoding.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lovemesomecoding.dto.UserMapper;
import io.swagger.annotations.Api;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Controller")
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserMapper userMapper;
}
