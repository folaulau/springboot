package com.lovemesomecoding.aws.sqs;

public interface SQSMessageAction {

	// user's actions
	public static final String SEND_USER_WELCOME_EMAIL = "SEND_USER_WELCOME_EMAIL";
	
	// payment's actions
	public static final String SEND_PAYMENT_RECEIPT_EMAIL = "SEND_PAYMENT_RECEIPT_EMAIL";
}
