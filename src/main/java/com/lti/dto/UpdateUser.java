package com.lti.dto;

import com.lti.entity.User;

public class UpdateUser {

	String message;
	User user;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
