package com.lovemesomecoding.aws.sqs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SQSListener sqsListener;
	
	@PostConstruct
	public void init() {
		sqsListener.processAccountQueue();
		
		//sqsListener.processAccountQueue1();
	}
}
