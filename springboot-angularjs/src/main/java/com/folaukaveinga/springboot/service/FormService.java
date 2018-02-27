package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.Form;
import com.folaukaveinga.springboot.domain.FormField;
import com.folaukaveinga.springboot.domain.InputField;

@Service
public class FormService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public Form save(Form form) {
		log.info(form.printAsJson());
		return form;
	}
}
