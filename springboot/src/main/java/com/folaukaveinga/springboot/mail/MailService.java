package com.folaukaveinga.springboot.mail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService implements Mail {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendSimpleMail(String to, String subject, String msg) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
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
