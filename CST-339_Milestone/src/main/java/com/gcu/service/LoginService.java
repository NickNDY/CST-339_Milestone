package com.gcu.service;

import com.gcu.model.LoginModel;
import org.springframework.stereotype.Service;

//Service layer
@Service
public class LoginService {

	/**
	 * Authenticates the user's login credentials
	 * Currently hard-coded to accept the password 'password'
	 * @param loginModel The input login model including the username and password
	 * @return True if valid, false if invalid
	 */
    public boolean checkCredentials(LoginModel loginModel)
    {
        // hardcoded password, will be removed when db is connected.
        return loginModel.getPassword().equals("password");
    }
}
