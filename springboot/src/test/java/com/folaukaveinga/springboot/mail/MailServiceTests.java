package com.folaukaveinga.springboot.mail;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {

	@Autowired
	private Mail mail;
	
	String testEmailRecipient = "testemail@gmail.com";
	
	@Test
	public void testSendSimpleMail() {
		boolean result = mail.sendSimpleMail(testEmailRecipient, "test mail", "Hello User. This is a test email");
		assertEquals(true, result);
	}
	
	@Test
	public void testSendAttachmentMail() {
		System.out.println("file path: files"+File.separator+"test_mail.pdf");
		boolean result = mail.sendAttachmentMail(testEmailRecipient, "test mail", "Hello User. This is a test email",("files"+File.separator+"test_mail.pdf"));
		assertEquals(true, result);
	}
	
	
	@Test
	public void testSendHtmlMail() {
		boolean result = mail.sendHtmlMail(testEmailRecipient, "test mail", "<b>Hello User</b>. <br><br><br><i>This is a test email</i>");
		assertEquals(true, result);
	}
	
	
	@Test
	public void testSendHtmlWithInlineImageMail() {
		
		StringBuilder msg = new StringBuilder();
		msg.append("<b>Hello User</b>.");
		msg.append("<br><br>");
		msg.append("<img src='cid:logo'>");// cid the img with logo id.
		msg.append("<br><br>");
		msg.append("<b>I thought you might like superman.</b>.");
		msg.append("<br><br>");
		msg.append("<img src='cid:logo'>");// cid the img with logo id.
		msg.append("<br><br>");
		msg.append("I hope you like it.");
		
		String imgLocation = "files"+File.separator+"superman.jpg";
		System.out.println("img path:"+imgLocation);
		
		boolean result = mail.sendHtmlWithInlineImageMail(testEmailRecipient, "test inline img mail",msg.toString(),imgLocation);
		assertEquals(true, result);
	}
}
