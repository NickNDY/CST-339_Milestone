package com.gcu.service;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	/**
	 * Checks whether a username is already taken or forbidden
	 * Currently hard-coded to prevent usernames of 'administrator' or 'admin'
	 * @param username The username to check
	 * @return True if taken, false if available
	 */
    public  boolean isUsernameTaken(String username)
    {
        return username.equalsIgnoreCase("administrator") || username.equalsIgnoreCase("admin");
    }
}
