package com.folaukaveinga.springboot.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomUtil {
	
	public static int getRandomNumber() {
		return RandomUtils.nextInt(1, Integer.MAX_VALUE);
	}
	
	public static String getRandomString() {
		return RandomStringUtils.randomAlphabetic(40);
	}
}
