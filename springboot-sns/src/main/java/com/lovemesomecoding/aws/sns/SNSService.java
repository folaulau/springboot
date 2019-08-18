package com.lovemesomecoding.aws.sns;

import java.util.List;

public interface SNSService {

	public String createTopic(String name);
	
	public boolean deleteTopic(String topicArn);
	
	public boolean text(String phone, String message);
	
	public boolean subscribePhone(String topicArn, String phone);
	
	public boolean subscribeEmail(String topicArn, String email);
	
	public boolean subscribeQueue(String topicArn, String queueUrl);
	
	public boolean unsubscribe(String subscriptionArn);
	
	public boolean sendMsgToTopic(String topicArn, String msg);
	
	public List<String> getAllTopicArns();
	
	public List<String> getSubscriptionsByTopic(String topicArn);
}
