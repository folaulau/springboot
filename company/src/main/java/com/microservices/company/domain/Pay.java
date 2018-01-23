package com.microservices.company.domain;

public class Pay {
	private int userId;
	private String period;
	
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pay(int userId, String period) {
		super();
		this.userId = userId;
		this.period = period;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "Pay [userId=" + userId + ", period=" + period + "]";
	}
	
}
