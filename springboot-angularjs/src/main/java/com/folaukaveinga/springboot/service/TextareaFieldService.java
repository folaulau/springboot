package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.FormField;
import com.folaukaveinga.springboot.domain.TextareaField;

@Service
public class TextareaFieldService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public TextareaField save(TextareaField textareaField) {
		log.info(textareaField.printAsJson());
		return textareaField;
	}
}
