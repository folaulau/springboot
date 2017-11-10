package com.folaukaveinga.springboot.jms;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	 @Autowired 
	 private JmsTemplate jmsTemplate;
	 
	 @Value("${queue.sender-receiver}")
	 private String queue;
	 
	public void sendMail(Mail mail){
		try {
			jmsTemplate.convertAndSend(queue, mail);
			System.out.println("Mail sent to queue, '"+queue+"', at "+LocalDateTime.now());
		} catch (Exception e) {
			System.out.println("Mail not sent");
			System.out.println("Exception, msg: "+e.getMessage());
		}
		
	}
}
