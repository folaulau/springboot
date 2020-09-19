package com.lovemesomecoding.email;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	
	// This is for aws ses mail. Sender email needs to be verified first before using it as a sender email.
	@Value("${spring.mail.properties.mail.sender}")
	private String sender;

	@Override
	public boolean sendSimpleMail(String to, String subject, String msg) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		simpleMailMessage.setFrom(sender);
		try {
			mailSender.send(simpleMailMessage);
			return true;
		} catch (Exception e) {
			System.out.println("Exception, send. msg: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendAttachmentMail(String to, String subject, String msg, String attachmentPath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg);
			helper.setFrom(sender);
			ClassPathResource attachment = new ClassPathResource(attachmentPath);
			helper.addAttachment(attachment.getFile().getName(), attachment.getFile());
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println("Exception, send. msg: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("Exception, send. msg: " + e.getMessage());
			return false;
		}

	}

	
	/*
	 * It's recommended that when sending html emails that you use table to structure and style
	 * your email.
	 * */
	@Override
	public boolean sendHtmlMail(String to, String subject, String msg) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
			helper.setFrom(sender);
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println("Exception, send. msg: " + e.getMessage());
			return false;
		}
	}

	
	/*
	 * It's recommended that when sending html emails that you use table to structure and style
	 * your email.
	 * */
	@Override
	public boolean sendHtmlWithInlineImageMail(String to, String subject, String msg, String logoPath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg,true);
			helper.setFrom(sender);
			// cid:log
			helper.addInline("logo", new ClassPathResource(logoPath));
			
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println("Exception, send. msg: " + e.getMessage());
			return false;
		}
	}

}
