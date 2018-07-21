package com.folaukaveinga.client.util;

import java.util.Base64;

public class HttpUtils {
	

	public static String basicAuthentication(String username, String password) {
		String auth = username + ":" + password;
		return "Basic " + new String( Base64.getEncoder().encodeToString(auth.getBytes()));
	}

}
