package com.lovemesomecoding.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.jms.Sender;
import com.lovemesomecoding.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailService {

	@Autowired
	private Sender sender;
	
	public boolean sendMail(Mail mail){
        log.info("sendMail, mail={}", ObjectUtils.toJson(mail));
		try {
			sender.sendMail(mail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
