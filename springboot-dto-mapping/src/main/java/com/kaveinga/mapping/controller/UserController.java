package com.kaveinga.mapping.controller;
import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaveinga.mapping.dto.UserCreateDTO;
import com.kaveinga.mapping.dto.UserMobDTO;
import com.kaveinga.mapping.dto.UserWebDTO;
import com.kaveinga.mapping.mapper.UserMapper;
import com.kaveinga.mapping.model.User;
import com.kaveinga.mapping.service.UserService;
import com.kaveinga.mapping.utility.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "users",produces = "Rest API for User operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping
	public ResponseEntity<UserWebDTO> create(@ApiParam(name="User", required=true, value="user") @Valid @RequestBody UserCreateDTO userCreateDTO){
		log.info("create(..)");
		log.info("UserCreateDto: {}",ObjectUtils.toJson(userCreateDTO));
		
		User user = userMapper.userCreateDtoToUser(userCreateDTO);
		
		log.info("User after transformed: {}",ObjectUtils.toJson(user));
		
		User createdUser = userService.create(user);
		
		log.info("User after created: {}",ObjectUtils.toJson(createdUser));
		
		UserWebDTO createdUserWebDTO = userMapper.userToUserWebDTO(createdUser);
		
		log.info("UserWebDTO: {}",ObjectUtils.toJson(createdUserWebDTO));
		
		return new ResponseEntity<UserWebDTO>(createdUserWebDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserMobDTO> getById(@PathVariable("id") Long id){
		log.info("getById({})", id);
		User createdUser = userService.getById(id);
		
		log.info("User after created: {}",ObjectUtils.toJson(createdUser));
		
		UserMobDTO createdUserMobDTO = userMapper.userToUserMobDTO(createdUser);
		
		log.info("UserMobDTO: {}",ObjectUtils.toJson(createdUserMobDTO));
		
		return new ResponseEntity<UserMobDTO>(createdUserMobDTO, HttpStatus.OK);
	}
}
