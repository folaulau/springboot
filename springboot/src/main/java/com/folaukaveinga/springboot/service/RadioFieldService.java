package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.RadioField;

@Service
public class RadioFieldService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public RadioField save(RadioField radioField) {
		log.info(radioField.printAsJson());
		return radioField;
	}
	
	
	public RadioField saveInputField(RadioField radioField) {
		log.info(radioField.printAsJson());
		return radioField;
	}
}
