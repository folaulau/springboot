package com.lovemesomecoding.aws.sns;

public interface SNSService {

	public String createTopic(String name);
	
	public boolean text(String phone, String message);
	
	public boolean subscribePhone(String topicArn, String phone);
	
	public boolean subscribeEmail(String topicArn, String email);
	
	public boolean subscribeQueue(String topicArn, String queueUrl);
	
	public boolean unsubscribe(String subscriptionArn);
	
	public boolean sendMsgToTopic(String topicArn, String msg);
}
