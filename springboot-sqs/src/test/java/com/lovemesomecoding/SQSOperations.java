package com.lovemesomecoding;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lovemesomecoding.aws.sqs.SQSService;
import com.lovemesomecoding.utils.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SQSOperations {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SQSService sqsService;
	
	@Test
	public void createQueue() {
		String name = "test";
		String queueUrl = sqsService.createQueue(name);
		
		log.info("queueUrl: {}", queueUrl);
	}
	
	@Test
	public void createFIFOQueue() {
		String name = "test";
		String queueUrl = sqsService.createFIFOQueue(name);
		
		log.info("FIFO queueUrl: {}", queueUrl);
	}
	
	@Test
	public void getAllQueues() {
		List<String> queueUrls = sqsService.getAllQueueUrls();
		
		log.info("queueUrls: {}", ObjectUtils.toJson(queueUrls));
	}

	@Test
	public void deleteAllQueues() {
		boolean result = sqsService.deleteAllQueues();
		
		log.info("all queues deleted: {}", result);
	}

}
