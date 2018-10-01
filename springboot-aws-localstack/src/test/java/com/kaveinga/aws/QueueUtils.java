package com.kaveinga.aws;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueUtils {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmazonSQS amazonSQS;

	@Test
	public void listQueues() {
		log.info("Listing all queues in your account.");
		try {
			ListQueuesResult listQueuesResult = amazonSQS.listQueues();
			if(listQueuesResult!=null && listQueuesResult.getQueueUrls().size()>0) {
				for (String queueUrl : listQueuesResult.getQueueUrls()) {
					log.info("QueueUrl: " + queueUrl);
				}
			}
		} catch (Exception e) {
			log.warn("Exception, msg: {}",e.getLocalizedMessage());
		}
		
		log.info("done listing queues!");
	}

	@Test
	public void createQueues() {
		log.info("reateQueues()");
		for (String profile : ConstantUtils.PROFILES) {
			for (String queueName : ConstantUtils.QUEUE_NAMES) {
				log.info("Create queue " + profile + "-" + queueName);
				try {
					CreateQueueRequest createQueueRequest = new CreateQueueRequest()
							.withQueueName(profile + "-" + queueName);
					 CreateQueueResult result = amazonSQS.createQueue(createQueueRequest);
					log.info("queue url: " + result.getQueueUrl());
					log.info("queue: " + result.toString());
				} catch (Exception e) {
					log.warn("Exception, msg: {}", e.getLocalizedMessage());
				}

			}
		}

	}

	@Test
	public void createQueue() {
		log.info("reateQueue()");
		String queueName = "test-queue";
		for (String profile : ConstantUtils.PROFILES) {
			try {

				CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(profile + "-" + queueName);
				String queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
				log.info("queue url: " + queueUrl);

			} catch (Exception e) {
				log.warn("Exception, msg: {}", e.getLocalizedMessage());
			}

		}
	}

	// @Ignore
	@Test
	public void deleteAllQueues() {
		log.debug("Deleting all queues.");
		for (String queueUrl : amazonSQS.listQueues().getQueueUrls()) {
			log.debug("queue url to delete: " + queueUrl);
			DeleteQueueResult result = amazonSQS.deleteQueue(new DeleteQueueRequest(queueUrl));
			log.debug("deleted result: " + result.toString());
		}
		log.debug("\n");
	}
}
