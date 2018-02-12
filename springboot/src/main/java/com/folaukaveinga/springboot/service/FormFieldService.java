package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.FormField;
import com.folaukaveinga.springboot.domain.InputField;

@Service
public class FormFieldService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public FormField save(FormField formField) {
		log.info(formField.printAsJson());
		return formField;
	}
	
	
	public InputField saveInputField(InputField inputField) {
		log.info(inputField.printAsJson());
		return inputField;
	}
}
