package com.kaveinga;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		String computername=InetAddress.getLocalHost().getHostName();
		System.out.println("computer name: "+computername);
	}

}
