package com.lovemesomecoding.aws.sqs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;

@Service
public class SQSService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmazonSQS amazonSQS;

	public String createQueue(String name) {
		CreateQueueRequest createQueueRequest = new CreateQueueRequest()
				.withQueueName(name);
		CreateQueueResult result = amazonSQS.createQueue(createQueueRequest);
		log.debug("queue url: " + result.getQueueUrl());
		log.debug("queue: " + result.toString());
		return result.getQueueUrl();
	}
	
	public String createFIFOQueue(String name) {
		final Map<String, String> attributes = new HashMap<>();
		attributes.put("FifoQueue", "true");
		attributes.put("ContentBasedDeduplication", "true");
		CreateQueueRequest createQueueRequest = new CreateQueueRequest()
				.withQueueName(name + ".fifo")
				.withAttributes(attributes);
		CreateQueueResult result = amazonSQS.createQueue(createQueueRequest);
		log.debug("queue url: " + result.getQueueUrl());
		log.debug("queue: " + result.toString());
		return result.getQueueUrl();
	}
	
	public void printAllQueueUrls() {
		for (String queueUrl : amazonSQS.listQueues().getQueueUrls()) {
			log.debug("queue url: " + queueUrl);
		}
	}
	
	public List<String> getAllQueueUrls() {
		return amazonSQS.listQueues().getQueueUrls();
	}

	public boolean deleteAllQueues() {
		for (String queueUrl : amazonSQS.listQueues().getQueueUrls()) {

			log.debug("queue url to delete: " + queueUrl);
			DeleteQueueResult result = amazonSQS.deleteQueue(new DeleteQueueRequest(queueUrl));
			log.debug("queue delete http status code: {}", result.getSdkHttpMetadata().getHttpStatusCode());

		}
		
		log.debug("done deleting queues!");
		return false;
	}
	

}
