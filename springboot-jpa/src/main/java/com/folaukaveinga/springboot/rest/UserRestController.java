package com.folaukaveinga.springboot.rest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folaukaveinga.springboot.domain.PaymentMethod;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.UserService;
import com.folaukaveinga.springboot.utility.Factory;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "user",produces = "Rest API for user operations", tags = "User API")
@RestController
@RequestMapping("api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Create user")
	@RequestMapping(value={"/",""}, method=RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user){
		log.info("create user: "+user.toString());
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "get User by id")
	@RequestMapping(value={"/{id}"}, method=RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id")int id,@RequestParam(name="include",required=false)String include){
		log.info("get user by id: {}", id);
		log.info("include: {}", include);
		User user = userService.get(id);
		String filter = include;//"id,paymentMethods";
				ObjectMapper mapper = Squiggly.init(new ObjectMapper(), filter);
		String userJson = SquigglyUtils.stringify(mapper, user);
		
		log.info(userJson);
		
		return new ResponseEntity<>(User.fromJson(userJson), HttpStatus.OK);
	}
	
	@ApiOperation(value = "get User PaymentMethods by user id")
	@RequestMapping(value={"/{id}/paymentmethods","/{id}/paymentmethods/"}, method=RequestMethod.GET)
	public ResponseEntity<Set<PaymentMethod>> getPaymentMethods(@PathVariable("id")int id){
		log.info("get user payment methods by user id: {}", id);
		
		
		Set<PaymentMethod> payments = userService.get(id).getPaymentMethods();
		String filter = "-user";
				ObjectMapper mapper = Squiggly.init(new ObjectMapper(), filter);
				mapper.enableDefaultTyping();
		String userJson = SquigglyUtils.stringify(mapper, payments);
		
		log.info(userJson);
		Set<PaymentMethod> p = null;
		try {
			p = Factory.getObjectMapper().readValue(userJson, mapper.getTypeFactory().constructCollectionType(Set.class,PaymentMethod.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	

	@ApiOperation(value = "Get all users")
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(Pageable page){
		log.info("get all users");
		log.info("page number: {}, page size: {}",page.getPageNumber(),page.getPageSize());
		log.info("page sort: {}",page.getSort());
		return new ResponseEntity<>(userService.getAll(page), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update user")
	@RequestMapping(value={"/update","/update/"}, method=RequestMethod.POST)
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update user: "+user.toString());
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
}
