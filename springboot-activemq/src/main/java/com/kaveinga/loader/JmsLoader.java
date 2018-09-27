package com.kaveinga.loader;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JmsLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConnectionFactory connectionFactory;

	@PostConstruct
	public void init() {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session  = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("user_sync");
			
//			MessageConsumer user = session.createConsumer(topic);
//			user.setMessageListener(new ConsumerMessageListener("user"));
//			
//			MessageConsumer userClientA = session.createConsumer(topic);
//			userClientA.setMessageListener(new ConsumerMessageListener("user_client_a"));
//			
//			MessageConsumer userClientB = session.createConsumer(topic);
//			userClientA.setMessageListener(new ConsumerMessageListener("user_client_b"));
//			
//			connection.start();
			
			
			
		} catch (JMSException e) {
			log.error("JMSException, msg: {}",e.getLocalizedMessage());
		}
	}

	public class ConsumerMessageListener implements MessageListener {

		private String consumerName;

		public ConsumerMessageListener(String consumerName) {

			this.consumerName = consumerName;

		}

		public void onMessage(Message message) {

			TextMessage textMessage = (TextMessage) message;

			try {
				log.info(consumerName + " received "+ textMessage.getText());
			} catch (JMSException e) {
				log.error("JMSException, msg: {}",e.getLocalizedMessage());
			}

		}

	}

}
