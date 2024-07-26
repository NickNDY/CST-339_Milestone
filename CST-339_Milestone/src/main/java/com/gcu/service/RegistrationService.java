package com.gcu.service;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public  boolean isUsernameTaken(String username)
    {
        return username.equalsIgnoreCase("administrator") || username.equalsIgnoreCase("admin");
    }
}
