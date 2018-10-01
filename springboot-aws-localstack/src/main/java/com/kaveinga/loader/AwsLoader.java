package com.kaveinga.loader;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;


@Component
public class AwsLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmazonSNS amazonSns;

	@Autowired
	private AmazonSQS amazonSQS;
	
	public static final List<String> QUEUE_NAMES = Arrays.asList("authentication-queue", "account-queue", "notification-queue", "expense-queue", "care-queue", "doctor-queue", "admin-queue",
			"account-account-sync-queue","notification-account-sync-queue", "expense-account-sync-queue", "care-account-sync-queue", "doctor-account-sync-queue","admin-account-sync-queue");

	public static final List<String> PROFILES = Arrays.asList("local");
	
	public static final String ACCOUNT_SYNC_TOPIC = "account-sync-topic";

	@PostConstruct
	public void init() {
		//loadSqs();
		
		//loadSns();
	}

	private void loadSqs() {
		if (isQueuesLoaded() == false) {
			createQueues();
		}
	}

	public boolean isQueuesLoaded() {
		return amazonSQS.listQueues().getQueueUrls().size() > 5;
	}

	public void createQueues() {
		log.info("reateQueues()");
		for (String profile : PROFILES) {
			for (String queueName : QUEUE_NAMES) {
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

	private void loadSns() {
		if(isTopicsLoaded()==false) {
			createTopic();
			
			subscribeQueuesToTopics();
		}
		
	}

	public void createTopic() {
		log.debug("createTopic()");

		for (String profile : PROFILES) {
			String topicName = ACCOUNT_SYNC_TOPIC;
			CreateTopicRequest createTopicRequest = new CreateTopicRequest();
			createTopicRequest.setName(profile + "-" + topicName);
			try {
				CreateTopicResult createTopicResult = amazonSns.createTopic(createTopicRequest);
				log.info("{} arn: {}", topicName, createTopicResult.getTopicArn());
			} catch (Exception e) {
				log.warn("Exception, msg: {}", e.getLocalizedMessage());
			}

		}
	}

	public void subscribeQueuesToTopics() {
		log.info("subscribeQueueToTopic()");
		List<Topic> topics = amazonSns.listTopics().getTopics();
		List<String> queueUrls = amazonSQS.listQueues().getQueueUrls();

		for (Topic topic : topics) {
			for (String queueUrl : queueUrls) {
				String subscription = "";
				if (topic.getTopicArn().contains("account-sync") && topic.getTopicArn().contains("local")
						&& queueUrl.contains("account-sync") && queueUrl.contains("local")) {
					log.info("topic arn: {}, queueUrl: {}", topic.getTopicArn(), queueUrl);
					subscription = Topics.subscribeQueue(amazonSns, amazonSQS, topic.getTopicArn(), queueUrl);
					log.info("subscription: {}", subscription);
				} else if (topic.getTopicArn().contains("account-sync") && topic.getTopicArn().contains("dev")
						&& queueUrl.contains("account-sync") && queueUrl.contains("dev")) {
					log.info("topic arn: {}, queueUrl: {}", topic.getTopicArn(), queueUrl);
					subscription = Topics.subscribeQueue(amazonSns, amazonSQS, topic.getTopicArn(), queueUrl);
					log.info("subscription: {}", subscription);
				}
			}
			System.out.println("\n");
		}
	}

	public boolean isTopicsLoaded() {
		return amazonSns.listTopics().getTopics().size()>0;
	}
}
