package com.folaukaveinga.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.model.User;
import com.folaukaveinga.validator.UserValidator;

@RestController
public class UserRestController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(value = "api/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid User user, BindingResult bindingResult) throws NoSuchMethodException, SecurityException, MethodArgumentNotValidException {
		log.info("creating user...");
		if (bindingResult.hasErrors()) {
			log.error("user is invalid");
			
			List<Map<String,String>> errors = new ArrayList<>();
			
			bindingResult.getFieldErrors().forEach((error)->{
				Map<String,String> erro = new HashMap<>();
				log.info("Error field: {}",error.getField());
				log.info("Error code: {}",error.getCode());
				log.info("Error default msg: {}",error.getDefaultMessage());
				log.info("Error object name: {}",error.getObjectName());
				log.info("Error rejected value: {}",error.getRejectedValue());
				System.out.println("\n");
				erro.put(error.getField(), error.getDefaultMessage());
				errors.add(erro);
			});
			
			bindingResult.getAllErrors().forEach(error -> {
				log.info("Error : {}",error.toString());
			});
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		log.info("user is valid");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
