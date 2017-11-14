package com.folaukaveinga.springboot.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groovy.transform.AutoClone;

@Service
public class SenderService {

	@Autowired
	private Sender sender;
	
	public boolean sendMail(Mail mail){
		
		try {
			sender.sendMail(mail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
