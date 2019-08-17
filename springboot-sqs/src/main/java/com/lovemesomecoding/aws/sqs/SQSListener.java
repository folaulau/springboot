package com.lovemesomecoding.aws.sqs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.lovemesomecoding.user.User;
import com.lovemesomecoding.user.UserService;
import com.lovemesomecoding.utils.ObjectUtils;

@Component
public class SQSListener {

	@Autowired
	private AmazonSQS amazonSQS;

	@Autowired
	private UserService userService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Async
	public void processAccountQueue() {
		int waitingSeconds = 10;
		String queueUrl = SQSQueue.ACCOUNTS_QUEUE_URL;

		final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
		receiveMessageRequest.withWaitTimeSeconds(waitingSeconds);

		while (true) {

			final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
			log.debug("polling {}", queueUrl);

			if (messages != null && messages.size() > 0) {
				for (final Message message : messages) {
					log.debug("Message Received");

					SQSMessage sqsMessage = SQSMessage.fromJson(message.getBody());

					int retryCount = 0;

					// retry policy
					while (true) {
						try {
							handleAccountMessage(sqsMessage);
							break;
						} catch (Exception e) {
							if (retryCount == 5) {
								break;
							}
							log.debug("retryCount={}", retryCount);
							retryCount++;
						}
					}

					// delete message once it's done
					DeleteMessageResult deleteMessageResult = amazonSQS
							.deleteMessage(new DeleteMessageRequest(queueUrl, message.getReceiptHandle()));

					log.debug("delete message response={}",
							ObjectUtils.toJson(deleteMessageResult.getSdkResponseMetadata()));

				}
			} else {
				log.debug("{} is empty", queueUrl);
			}
		}
	}
	
	
	/*
	 * If two applications or two threads from the same application
	 * are listening to a queue, Only one thread will process the message.
	 * processAccountQueue1 is a duplicate of processAccountQueue so that 
	 * two threads are listening to the ACCOUNTS_QUEUE. Only one of the listeners 
	 * gets to process the message.
	 */
//	@Async
//	public void processAccountQueue1() {
//		int waitingSeconds = 10;
//		String queueUrl = SQSQueue.ACCOUNTS_QUEUE_URL;
//
//		final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
//		receiveMessageRequest.withWaitTimeSeconds(waitingSeconds);
//
//		while (true) {
//
//			final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
//			log.debug("polling {}", queueUrl);
//
//			if (messages != null && messages.size() > 0) {
//				for (final Message message : messages) {
//
//					SQSMessage sqsMessage = SQSMessage.fromJson(message.getBody());
//
//					int retryCount = 0;
//
//					while (true) {
//						try {
//							handleAccountMessage(sqsMessage);
//							break;
//						} catch (Exception e) {
//							if (retryCount == 5) {
//								break;
//							}
//							log.debug("retryCount={}", retryCount);
//							retryCount++;
//						}
//					}
//
//					// delete message once it's done
//					DeleteMessageResult deleteMessageResult = amazonSQS
//							.deleteMessage(new DeleteMessageRequest(queueUrl, message.getReceiptHandle()));
//
//					log.debug("delete message response={}",
//							ObjectUtils.toJson(deleteMessageResult.getSdkResponseMetadata()));
//
//				}
//			} else {
//				log.debug("{} is empty", queueUrl);
//			}
//		}
//	}

	private void handleAccountMessage(SQSMessage sqsMessage) {

		if (sqsMessage.getType().equals(SQSMessageType.EVENT)) {

			String action = sqsMessage.getAction();

			
			User user = null;
			
			switch (action) {
				case SQSMessageAction.SEND_USER_WELCOME_EMAIL:
					
					user = ObjectUtils.fromJsonString(sqsMessage.getData(), User.class);
					
					log.debug("user={}",ObjectUtils.toJson(user));
					
					this.userService.sendWelcomeEmail(user);
				break;
			default:
				break;
			}

		} else if (sqsMessage.getType().equals(SQSMessageType.CRON_JOB)) {

		}
	}
}
