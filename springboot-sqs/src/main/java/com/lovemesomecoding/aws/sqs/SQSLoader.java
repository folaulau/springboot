package com.lovemesomecoding.aws.sqs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SQSLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void init() {
		
	}
	
	private boolean checkQueues() {
		
		return false;
	}
	
	private void createQueues() {
		
	}
}
