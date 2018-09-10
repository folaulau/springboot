package com.kaveinga.events;

import org.springframework.context.ApplicationEvent;
import com.kaveinga.user.User;

public class UserEvent extends ApplicationEvent{
	
	private User user;
	
	public UserEvent(User user) {
		super(user);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
