package com.folaukaveinga.springboot.rest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Account;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.AccountService;
import com.folaukaveinga.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(value = "account",produces = "Rest API for account operations", tags = "Account API")
@RestController
@RequestMapping("api/accounts")
public class AccountRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;

	
	@ApiOperation(value = "get Account by id")
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET)
	public ResponseEntity<Account> get(@PathVariable("id")String id){
		log.info("get account by id: {}", id);
		return new ResponseEntity<>(accountService.getById(UUID.fromString(id)), HttpStatus.OK);
	}
}
