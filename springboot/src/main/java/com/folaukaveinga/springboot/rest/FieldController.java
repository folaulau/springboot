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

import com.folaukaveinga.springboot.domain.CheckboxField;
import com.folaukaveinga.springboot.domain.Form;
import com.folaukaveinga.springboot.domain.InputField;
import com.folaukaveinga.springboot.domain.RadioField;
import com.folaukaveinga.springboot.domain.TextareaField;
import com.folaukaveinga.springboot.service.CheckboxFieldService;
import com.folaukaveinga.springboot.service.FormFieldService;
import com.folaukaveinga.springboot.service.FormService;
import com.folaukaveinga.springboot.service.InputFieldService;
import com.folaukaveinga.springboot.service.RadioFieldService;
import com.folaukaveinga.springboot.service.TextareaFieldService;


@RestController
@RequestMapping("api/field")
public class FieldController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FormFieldService formFieldService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private CheckboxFieldService checkboxFieldService;
	
	@Autowired
	private RadioFieldService radioFieldService;
	
	@Autowired
	private TextareaFieldService textareaFieldService;
	
	@Autowired
	private InputFieldService inputFieldService;
	
	@RequestMapping(value={"/input/{id}","/input/{id}/"}, method=RequestMethod.GET)
	public ResponseEntity<Boolean> sendMail(@PathVariable("id") long id){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/input","/input/"}, method=RequestMethod.POST)
	public ResponseEntity<InputField> saveInputField(@RequestBody InputField inputField){
		log.info("save input field");
		inputField = inputFieldService.save(inputField);
		return new ResponseEntity<>(inputField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/radio","/radio/"}, method=RequestMethod.POST)
	public ResponseEntity<RadioField> saveRadioField(@RequestBody RadioField radioField){
		log.info("save radio field");
		radioField = radioFieldService.save(radioField);
		return new ResponseEntity<>(radioField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/checkbox","/checkbox/"}, method=RequestMethod.POST)
	public ResponseEntity<CheckboxField> saveCheckbox(@RequestBody CheckboxField checkboxField){
		log.info("save checkbox field");
		checkboxField = checkboxFieldService.save(checkboxField);
		return new ResponseEntity<>(checkboxField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/checkbox-with-input","/checkbox-with-input/"}, method=RequestMethod.POST)
	public ResponseEntity<CheckboxField> saveCheckboxWithInput(@RequestBody CheckboxField checkboxField){
		log.info("save checkbox with input field");
		checkboxField = checkboxFieldService.save(checkboxField);
		return new ResponseEntity<>(checkboxField, HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/radio-with-textarea","/radio-with-textarea/"}, method=RequestMethod.POST)
	public ResponseEntity<RadioField> saveRadioWithTextarea(@RequestBody RadioField radioField){
		log.info("save radio btns with textarea field");
		radioField = radioFieldService.save(radioField);
		return new ResponseEntity<>(radioField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/textarea","/textarea/"}, method=RequestMethod.POST)
	public ResponseEntity<TextareaField> saveTextarea(@RequestBody TextareaField textareaField){
		log.info("save textarea field");
		textareaField = textareaFieldService.save(textareaField);
		return new ResponseEntity<>(textareaField, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/form","/form/"}, method=RequestMethod.POST)
	public ResponseEntity<Form> saveForm(@RequestBody Form form){
		log.info("save textarea field");
		form = formService.save(form);
		return new ResponseEntity<>(form, HttpStatus.OK);
	}
}
