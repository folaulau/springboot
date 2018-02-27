package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.InputField;
import com.folaukaveinga.springboot.domain.TextareaField;

@Service
public class InputFieldService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public InputField save(InputField inputField) {
		log.info(inputField.printAsJson());
		return inputField;
	}
}
