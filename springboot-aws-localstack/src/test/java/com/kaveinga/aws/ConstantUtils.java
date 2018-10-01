package com.kaveinga.aws;

import java.util.Arrays;
import java.util.List;

public final class ConstantUtils {

	public static final List<String> QUEUE_NAMES = Arrays.asList("authentication-queue", "account-queue", "notification-queue", "expense-queue", "care-queue", "doctor-queue", "admin-queue",
			"account-account-sync-queue","notification-account-sync-queue", "expense-account-sync-queue", "care-account-sync-queue", "doctor-account-sync-queue","admin-account-sync-queue");

	public static final List<String> PROFILES = Arrays.asList("local");
	
	public static final String ACCOUNT_SYNC_TOPIC = "account-sync-topic";
}
