package com.lovemesomecoding.aws.sns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.DeleteTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sns.model.UnsubscribeResult;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;

@Service
public class SNSServiceImp implements SNSService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AmazonSNS amazonSNS;
	
	@Autowired
	private AmazonSQS amazonSQS;
	
	@Override
	public boolean text(String phone, String message) {
		PublishResult result = amazonSNS.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber("+1"+phone)
                .withMessageAttributes(new HashMap<String, MessageAttributeValue>()));
		
		return (result.getMessageId()!=null) ? true : false;
	}

	@Override
	public String createTopic(String name) {
		final CreateTopicRequest createTopicRequest = new CreateTopicRequest(name);
		CreateTopicResult createTopicResult = amazonSNS.createTopic(createTopicRequest);

		return createTopicResult.getTopicArn();
	}

	@Override
	public boolean subscribePhone(String topicArn, String phone) {
		final SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "sms", "+1"+phone);
		amazonSNS.subscribe(subscribeRequest);
		return true;
	}

	@Override
	public boolean subscribeEmail(String topicArn, String email) {
		final SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "email", email);
		amazonSNS.subscribe(subscribeRequest);
		return true;
	}

	@Override
	public boolean unsubscribe(String subscriptionArn) {
		UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest(subscriptionArn);
		
		UnsubscribeResult unsubscribeResult = amazonSNS.unsubscribe(unsubscribeRequest);
		
		return (unsubscribeResult!=null) ? true : false;
	}

	@Override
	public boolean sendMsgToTopic(String topicArn, String msg) {
		final PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		final PublishResult publishResponse = amazonSNS.publish(publishRequest);
		return (publishResponse.getMessageId()!=null) ? true : false;
	}

	@Override
	public boolean subscribeQueue(String topicArn, String queueUrl) {
		String subscriptionArn = Topics.subscribeQueue(amazonSNS, amazonSQS, topicArn, queueUrl);
		return (subscriptionArn!=null && subscriptionArn.isEmpty()==false) ? true : false;
	}

	@Override
	public List<String> getAllTopicArns() {
		ListTopicsResult listTopicsResult = amazonSNS.listTopics();
		List<String> topicArns = new ArrayList<>();
		for(Topic topic : listTopicsResult.getTopics()){
			topicArns.add(topic.getTopicArn());
		}
		return topicArns;
	}

	@Override
	public List<String> getSubscriptionsByTopic(String topicArn) {
		ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = amazonSNS.listSubscriptionsByTopic(topicArn);
		List<String> subscriptionArns = new ArrayList<>();
		for(Subscription subscription : listSubscriptionsByTopicResult.getSubscriptions()){
			subscriptionArns.add(subscription.getSubscriptionArn());
		}
		return subscriptionArns;
	}

	@Override
	public boolean deleteTopic(String topicArn) {
		final DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
		DeleteTopicResult deleteTopicResult = amazonSNS.deleteTopic(deleteTopicRequest);
		return (deleteTopicResult!=null) ? true : false;
	}



}
