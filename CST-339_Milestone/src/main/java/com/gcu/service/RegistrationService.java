package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.repository.UserRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private UserRepository repository;
	
	/**
	 * Checks whether a username is already taken or forbidden (admin/administrator)
	 * @param username The username to check
	 * @return True if taken, false if available
	 */
    public  boolean isUsernameTaken(String username)
    {
        return username.equalsIgnoreCase("administrator") || username.equalsIgnoreCase("admin") || repository.findByUsernameIgnoreCase(username) == null;
    }

    /**
     * Checks whether an email is already used
     * @param username
     * @return True if taken, false if available
     */
    public  boolean isEmailUsed(String email)
    {
        return repository.findByEmailIgnoreCase(email) == null;
    }
}
