/*******************************************************************************
 * @ MessageDirector.java
 * @ Project: SideCar Health Corporation
 *
 * Copyright (c) 2018 SideCar Health Corporation. - All Rights Reserved
 * El Segundo, California, U.S.A.
 *
 * This software is the confidential and proprietary information of
 * SideCar Health Corporation. ("Confidential Information").
 *
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with SideCar Corporation.
 *
 * Contributors:
 * SideCar Health Corporation. - Software Engineering Team
 ******************************************************************************/
package com.lovemesomecoding.aws.sqs;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovemesomecoding.utils.ObjectUtils;

public class SQSMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// type of msg[event or job]
	private String type;
	private String action;
	private String data;
	
	public SQSMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SQSMessage(String type, String action, String data) {
		super();
		this.type = type;
		this.action = action;
		this.data = data;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toJson() {
		try {
			return ObjectUtils.getObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			System.out.println("SQSMessage, msg: "+e.getLocalizedMessage());
			return "{}";
		}
	}
	
	public static SQSMessage fromJson(String json) {
		try {
			return ObjectUtils.getObjectMapper().readValue(json, SQSMessage.class);
		} catch (Exception e) {
			System.out.println("SQSMessage, msg: "+e.getLocalizedMessage());
			return null;
		}
	}
}
