package com.lovemesomecoding.aws.sqs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lovemesomecoding.utils.ObjectUtils;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Service
public class SQSService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AmazonSQS amazonSQS;
	
	
	public boolean sendMessageToAccountQueue(SQSMessage msg) {
		return sendMessage(SQSQueue.ACCOUNTS_QUEUE_URL,msg);
	}
	
	public boolean sendMessage(String queueUrl, SQSMessage msg) {
		return sendMessage(queueUrl,msg,0);
	}
	
	public boolean sendMessage(String queueUrl, SQSMessage msg, int delaySeconds) {
		return sendMessage(queueUrl,msg,delaySeconds,null);
	}

	public boolean sendMessage(String queueUrl, SQSMessage msg, int delaySeconds, Map<String, MessageAttributeValue> messageAttributes) {
		
		if(msg==null) {
			throw new RuntimeException("msg is empty");
		}
		
		SendMessageRequest sendMessageRequest = new SendMessageRequest();
		sendMessageRequest.setQueueUrl(queueUrl);
		sendMessageRequest.setMessageBody(msg.toJson());
		
		if(delaySeconds>0) {
			sendMessageRequest.setDelaySeconds(delaySeconds);
		}
		
		if(messageAttributes!=null) {
			sendMessageRequest.withMessageAttributes(messageAttributes);
		}
		
		SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);

		String resultId = sendMessageResult.getMessageId();

		log.info("resultId={}", resultId);

		return true;
	}

	/**
	 * 
	 * messageGroupId (This parameter applies only to FIFO (first-in-first-out)
	 * queues.) - The tag that specifies that a message belongs to a specific
	 * message group. Messages that belong to the same message group are processed
	 * in a FIFO manner (however, messages in different message groups might be
	 * processed out of order). To interleave multiple ordered streams within a
	 * single queue, use <code>MessageGroupId</code> values (for example, session
	 * data for multiple users). In this scenario, multiple consumers can process
	 * the queue, but the session data of each user is processed in a FIFO fashion.
	 * 
	 * MessageDeduplicationId (This parameter applies only to FIFO
	 * (first-in-first-out) queues) - The token used for deduplication of sent
	 * messages. If a message with a particular <code>MessageDeduplicationId</code>
	 * is sent successfully, any messages sent with the same
	 * <code>MessageDeduplicationId</code> are accepted successfully but aren't
	 * delivered during the 5-minute deduplication interval. If you send one message
	 * with <code>ContentBasedDeduplication</code> enabled and then another message
	 * with a <code>MessageDeduplicationId</code> that is the same as the one
	 * generated for the first <code>MessageDeduplicationId</code>, the two messages
	 * are treated as duplicates and only one copy of the message is delivered.
	 * 
	 * delaySeconds - only set on the queue level.
	 * 
	 * @param msg
	 * @return
	 */
	public boolean sendMessageToFIFOQueue(String queueUrl, SQSMessage msg, String groupId, Map<String, MessageAttributeValue> messageAttributes) {
		
		if(msg==null) {
			throw new RuntimeException("msg is empty");
		}
		
		SendMessageRequest sendMessageRequest = new SendMessageRequest();
		sendMessageRequest.setQueueUrl(queueUrl);
		sendMessageRequest.setMessageBody(msg.toJson());
		
		if(messageAttributes!=null) {
			sendMessageRequest.withMessageAttributes(messageAttributes);
		}

		String msgId = RandomGeneratorUtils.getQueueMsgId();

		// make every message unique
		sendMessageRequest.setMessageDeduplicationId(msgId);
		
		
		// group related messages together to process in FIFO order.
		if(groupId!=null && groupId.isEmpty()==false) {
			sendMessageRequest.setMessageGroupId(groupId);
		}else {
			sendMessageRequest.setMessageGroupId("groupId");
		}

		SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);

		String resultId = sendMessageResult.getMessageId();

		log.info("resultId={}", resultId);

		return true;
	}
	
	@Async
	public void receiveQueueMessage(String queueUrl) {
		int waitingSeconds = 10;
		
		final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
		receiveMessageRequest.withWaitTimeSeconds(waitingSeconds);
		
		while(true) {
			
			final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
			log.debug("polling {}", queueUrl);
			
			if(messages!=null && messages.size()>0) {
				for (final Message message : messages) {
				    log.debug("Message Received");
				    log.debug("  MessageId:     " + message.getMessageId());
				    log.debug("  ReceiptHandle: " + message.getReceiptHandle());
				    log.debug("  MD5OfBody:     " + message.getMD5OfBody());
				    log.debug("  Body:          " + message.getBody());
				    for (final Entry<String, String> entry : message.getAttributes().entrySet()) {
				    	log.debug("Attribute");
				    	log.debug("  Name:  " + entry.getKey());
				    	log.debug("  Value: " + entry.getValue());
				    }
				    
				    
				    // delete message once it's done
				    DeleteMessageResult deleteMessageResult = amazonSQS.deleteMessage(new DeleteMessageRequest(queueUrl, message.getReceiptHandle()));
				    
				    log.debug("delete message response={}", ObjectUtils.toJson(deleteMessageResult.getSdkResponseMetadata()));
				
				}
			}else {
				log.debug("{} is empty",queueUrl);
			}
		}
	}

	public String createQueue(String name) {
		CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(name);
		CreateQueueResult result = amazonSQS.createQueue(createQueueRequest);
		log.debug("queue url: " + result.getQueueUrl());
		log.debug("queue: " + result.toString());
		return result.getQueueUrl();
	}

	/**
	 * Set delaySeconds if needed as it would be able to do at message sending.
	 * @param name
	 * @return
	 */
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
