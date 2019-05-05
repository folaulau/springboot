package com.folaukaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.dto.UserDto;
import com.folaukaveinga.dto.UserMapper;
import com.folaukaveinga.role.Role;
import com.folaukaveinga.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Admin Controller")
@RestController
@RequestMapping("/admin")
public class UserAdminController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Secured(value={"ROLE_"+Role.ADMIN})
	@ApiOperation(value = "Get Member By Uuid")
	@GetMapping("/users/{uid}")
	public ResponseEntity<UserDto> getUserByUid(@RequestHeader(name="token", required=true) String token, @ApiParam(name="uid", required=true, value="uid") @PathVariable("uid") String uid){
		log.debug("getUserByUid(..)");
		
		User user = userService.getByUid(uid);
		
		UserDto userDto = userMapper.userToUserDto(user);
		
		log.debug("userDto: {}",ObjectUtils.toJson(userDto));
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
}
