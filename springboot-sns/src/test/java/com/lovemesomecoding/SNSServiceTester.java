package com.lovemesomecoding;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.sns.util.Topics;
import com.lovemesomecoding.aws.sns.SNSService;
import com.lovemesomecoding.utils.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SNSServiceTester {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SNSService snsService;
	
	/*
	 * TOPICS
	 * news - arn:aws:sns:us-west-2:059920316347:news
	 */
	
	@Test
	public void createTopic() {
		String topicName = "news";
		
		String topicArn = snsService.createTopic(topicName);
		
		log.info("topicArn={}",topicArn);
	}
	
	@Test
	public void text() {
		String phone = "3109934731";
		
		String message = "test message";
		
		boolean sent = snsService.text(phone, message);
		
		log.info("sent={}",sent);
	}
	
	@Test
	public void subscribeEmail() {
		String newsTopic = "arn:aws:sns:us-west-2:059920316347:news";
		String email = "folaukaveinga@gmail.com";
		
		boolean sub = snsService.subscribeEmail(newsTopic, email);
		
		log.info("sub={}",sub);
		
//		Topics.subscribeQueue(sns, sqs, snsTopicArn, sqsQueueUrl);
	}
	
	@Test
	public void subscribePhone() {
		String newsTopic = "arn:aws:sns:us-west-2:059920316347:news";
		String phone = "9519235276";
		
		boolean sub = snsService.subscribePhone(newsTopic, phone);
		
		log.info("sub={}",sub);
	}
	
	@Test
	public void subscribeQueue() {
		String newsTopic = "arn:aws:sns:us-west-2:059920316347:news";
//		String accountsQueueUrl = "https://sqs.us-west-2.amazonaws.com/059920316347/accounts";
//		
//		boolean sub = snsService.subscribeQueue(newsTopic, accountsQueueUrl);
//		
//		log.info("sub={}",sub);
		
		String testFIFOQueueUrl = "https://sqs.us-west-2.amazonaws.com/059920316347/test.fifo";
		
		boolean sub = snsService.subscribeQueue(newsTopic, testFIFOQueueUrl);
		
		log.info("sub={}",sub);
	}
	
	@Test
	public void sendMsgToTopic() {
		String newsTopic = "arn:aws:sns:us-west-2:059920316347:news";
		String msg = "test msg to news topic";
		
		boolean sub = snsService.sendMsgToTopic(newsTopic, msg);
		
		log.info("sub={}",sub);
	}
	
	@Test
	public void getAllTopicArns() {
		
		List<String> topicArns = snsService.getAllTopicArns();
		
		log.info("topicArns={}",ObjectUtils.toJson(topicArns));
	}
	
	@Test
	public void getSubscriptionsByTopic() {
		
		List<String> topicArns = snsService.getAllTopicArns();
		
		log.info("topicArns={}",ObjectUtils.toJson(topicArns));
		
		topicArns.forEach((topicArn)->{
			List<String> subscriptionArns = snsService.getSubscriptionsByTopic(topicArn);
			log.info("subscriptionArns={}",ObjectUtils.toJson(subscriptionArns));
		});
		
	}
	
	@Test
	public void deleteAllTopicsAndSubscriptions() {
		
		List<String> topicArns = snsService.getAllTopicArns();
		
		log.info("topicArns={}",ObjectUtils.toJson(topicArns));
		
		topicArns.forEach((topicArn)->{
			List<String> subscriptionArns = snsService.getSubscriptionsByTopic(topicArn);
			log.info("subscriptionArns={}",ObjectUtils.toJson(subscriptionArns));
			
			// unsubscribe first
			subscriptionArns.forEach((subscriptionArn)->{
				boolean unsubscribe = snsService.unsubscribe(subscriptionArn);
				log.info("unsubscribe={}",unsubscribe);
			});
			
			
			boolean deleteResult = snsService.deleteTopic(topicArn);
			log.info("deleteResult={}",deleteResult);
		});
		
	}

}
