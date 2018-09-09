package com.kaveinga.account;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "accounts",produces = "Rest API for account operations", tags = "Account Controller")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Create Account")
	@PostMapping
	public ResponseEntity<Account> create(@RequestBody Account account){
		log.info("create(..)", account.toJson());
		account = accountService.create(account);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping
	public ResponseEntity<Account> update(@RequestBody Account account){
		log.info("update({})", account.toJson());
		account = accountService.update(account);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get By Id")
	@GetMapping("/{id}")
	public ResponseEntity<Account> getById(@PathVariable("id") Long id){
		log.info("getById({})", id);
		Account user = accountService.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Users")
	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAll(){
		log.info("getAll()");
		List<Account> accounts = accountService.getAll();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Delete By Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		log.info("delete({})", id);
		
		this.accountService.remove(id);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
