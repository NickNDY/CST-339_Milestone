package com.gcu.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
/**
 * Intended to store user information per session to allow users to stay logged in for the length of their session
 */
public class SessionState {
	private String username;

	public SessionState(String username) {
		this.username = username;
	}
	
	public SessionState()
	{
		this.username = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
