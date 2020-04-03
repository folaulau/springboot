package com.folaukaveinga.springboot.jms;

import java.time.LocalDateTime;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	
	
    @JmsListener(destination = "${queue.sender-receiver}")
    public void receiveMessage(String mailFromJson) {
        System.out.println("Received mail "+LocalDateTime.now());
        try {
        	Mail mail = Mail.fromJson(mailFromJson);
        	mail.print();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
        
    }
}