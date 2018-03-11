package com.folaukaveinga.api.model;

import java.io.Serializable;
import java.util.List;

public class Status  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Status [status=" + status + ", message=" + message + "]";
	}
}
