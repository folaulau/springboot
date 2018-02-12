package com.folaukaveinga.springboot.rest;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.springboot.domain.InputField;
import com.folaukaveinga.springboot.domain.RadioField;
import com.folaukaveinga.springboot.service.FormFieldService;
import com.folaukaveinga.springboot.service.RadioFieldService;


@RestController
@RequestMapping("api/field")
public class FieldController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FormFieldService formFieldService;
	
	@Autowired
	private RadioFieldService radioFieldService;
	
	@RequestMapping(value={"/input/{id}","/input/{id}/"}, method=RequestMethod.GET)
	public ResponseEntity<Boolean> sendMail(@PathVariable("id") long id){
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/input","/input/"}, method=RequestMethod.POST)
	public ResponseEntity<InputField> saveInputField(@RequestBody InputField inputField){
		log.info("save input field");
		inputField = formFieldService.saveInputField(inputField);
		return new ResponseEntity<>(inputField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/radio","/radio/"}, method=RequestMethod.POST)
	public ResponseEntity<RadioField> saveRadioField(@RequestBody RadioField radioField){
		log.info("save radio field");
		radioField = radioFieldService.save(radioField);
		return new ResponseEntity<>(radioField, HttpStatus.OK);
	}
}
