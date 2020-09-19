package com.lovemesomecoding.email;

public interface EmailService {
	public boolean sendSimpleMail(String to, String subject, String msg);
	public boolean sendAttachmentMail(String to, String subject, String msg, String attachmentPath);
	public boolean sendHtmlMail(String to, String subject, String msg);
	public boolean sendHtmlWithInlineImageMail(String to, String subject, String msg, String logoPath);
}
