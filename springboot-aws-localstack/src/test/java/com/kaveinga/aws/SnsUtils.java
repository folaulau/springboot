package com.kaveinga.aws;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnsUtils {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmazonSNS amazonSns;

	@Autowired
	private AmazonSQS amazonSQS;

	@Test
	public void sendTestSms() {
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		String message = "test msg";
		String phoneNumber = "13109934731";
		smsAttributes.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue("sidecar").withDataType("String"));
		try {
			PublishResult result = amazonSns.publish(new PublishRequest().withMessage(message)
					.withPhoneNumber(phoneNumber).withMessageAttributes(smsAttributes));
			log.info("Message id: " + result.getMessageId());
		} catch (Exception e) {
			log.info("Error, msg: " + e.getMessage());
		}
	}

	@Test
	public void createTopic() {
		log.debug("createTopic()");

		for (String profile : ConstantUtils.PROFILES) {
			String topicName = ConstantUtils.ACCOUNT_SYNC_TOPIC;
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

	@Test
	public void subscribeQueuesToTopics() {
		log.info("subscribeQueueToTopic()");
		List<Topic> topics = amazonSns.listTopics().getTopics();
		List<String> queueUrls = amazonSQS.listQueues().getQueueUrls();

		for (Topic topic : topics) {
			for (String queueUrl : queueUrls) {
				String subscription = "";
				if(topic.getTopicArn().contains("account-sync") && topic.getTopicArn().contains("local") && queueUrl.contains("account-sync") && queueUrl.contains("local")) {
					log.info("topic arn: {}, queueUrl: {}", topic.getTopicArn(), queueUrl);
					subscription =Topics.subscribeQueue(amazonSns, amazonSQS, topic.getTopicArn(), queueUrl);
					log.info("subscription: {}",subscription);
				}else if(topic.getTopicArn().contains("account-sync") && topic.getTopicArn().contains("dev") && queueUrl.contains("account-sync") && queueUrl.contains("dev")) {
					log.info("topic arn: {}, queueUrl: {}", topic.getTopicArn(), queueUrl);
					subscription = Topics.subscribeQueue(amazonSns, amazonSQS, topic.getTopicArn(), queueUrl);
					log.info("subscription: {}",subscription);
				}
			}
			System.out.println("\n");
		}
	}
	
	@Test
	public void publishToTopic() {
		log.info("publishToTopic()");
		String topicArn = "arn:aws:sns:us-east-1:123456789012:local-account-sync-topic";
		PublishRequest publishRequest =new PublishRequest();
		publishRequest.setTopicArn(topicArn);
		publishRequest.setSubject("sync account");
		publishRequest.setMessage("Test message....");
		PublishResult publishResult = amazonSns.publish(publishRequest);
		log.info("message id: {}", publishResult.getMessageId());
	}
	
	@Test
	public void listTopics() {
		
		List<Topic> topics = amazonSns.listTopics().getTopics();

		for (Topic topic : topics) {
			log.info("TOPIC ARN: {}", topic.getTopicArn());
			ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = amazonSns.listSubscriptionsByTopic(topic.getTopicArn());
			List<Subscription> subs = listSubscriptionsByTopicResult.getSubscriptions();
			
			for(Subscription sub : subs) {
				log.info("SUBSCRIPTION ENDPOINT: {}",sub.getEndpoint());
			}
		}
		log.info("done listing topics!");
		
		
	}
	
	@Test
	public void removeAllTopics() {
		List<Topic> topics = amazonSns.listTopics().getTopics();

		for (Topic topic : topics) {
			amazonSns.deleteTopic(topic.getTopicArn());
		}
	}
}
