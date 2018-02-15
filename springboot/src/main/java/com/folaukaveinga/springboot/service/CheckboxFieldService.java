package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.CheckboxField;
import com.folaukaveinga.springboot.domain.RadioField;

@Service
public class CheckboxFieldService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public CheckboxField save(CheckboxField checkboxField) {
		log.info(checkboxField.printAsJson());
		return checkboxField;
	}
}
