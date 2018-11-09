package com.folaukaveinga.springboot.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

@Api(value = "user", produces = "Rest API for user operations", tags = "User API")
@RestController
@RequestMapping("api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create user")
	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		log.info("create user: " + user.toString());
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}

	@ApiOperation(value = "get User by id", response = User.class)
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id") int id,
			@RequestParam(name = "include", required = false) String include) {
		log.info("get user by id: {}", id);
		log.info("include: {}", include);
		User user = userService.get(id);

		System.out.println("time: " + Factory.getSidecarDateForm().format(new Date()));
		String filter = include;// "id,paymentMethods";
		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), filter);
		String userJson = SquigglyUtils.stringify(mapper, user);

		log.info(userJson);

		return new ResponseEntity<>(User.fromJson(userJson), HttpStatus.OK);
	}

	@ApiOperation(value = "get User PaymentMethods by user id")
	@RequestMapping(value = { "/{id}/paymentmethods" }, method = RequestMethod.GET)
	public ResponseEntity<Set<PaymentMethod>> getPaymentMethods(@PathVariable("id") int id) {
		log.info("get user payment methods by user id: {}", id);

		Set<PaymentMethod> payments = userService.get(id).getPaymentMethods();
		String filter = "-user";
		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), filter);
		mapper.enableDefaultTyping();
		String userJson = SquigglyUtils.stringify(mapper, payments);
		System.out.println("time: " + Factory.getSidecarDateForm().format(new Date()));
		log.info(userJson);
		Set<PaymentMethod> p = null;
		try {
			p = Factory.getObjectMapper().readValue(userJson,
					mapper.getTypeFactory().constructCollectionType(Set.class, PaymentMethod.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@ApiOperation(value = "Get all users")
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(Pageable page) {
		log.info("get all users");
		log.info("page number: {}, page size: {}", page.getPageNumber(), page.getPageSize());
		log.info("page sort: {}", page.getSort());
		System.out.println("time: " + Factory.getSidecarDateForm().format(new Date()));
		return new ResponseEntity<>(userService.getAll(page), HttpStatus.OK);
	}

	@ApiOperation(value = "Search all users")
	@RequestMapping(value = { "search", "/search" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> search(Pageable pageable,
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			@RequestParam(required = false, name = "sort") String sort,
			@RequestParam(required = false, name = "age") int age) {
		log.info("get all users");
		log.info("page number: {}, page size: {}", page, size);
		log.info("page sort: {}", sort);
		List<User> users = userService.search(age, pageable);
		log.info("user list size: {}", users.size());
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "Update user")
	@RequestMapping(value = { "/update" }, method = RequestMethod.PUT)
	public ResponseEntity<User> update(HttpServletRequest request) throws IOException {
		//log.info("update user: " + user.toString());

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		
		System.out.println("data: "+data);

		System.out.println("time: " + Factory.getSidecarDateForm().format(new Date()));
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
