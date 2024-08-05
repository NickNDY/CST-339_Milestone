package com.gcu.service;

import com.gcu.entity.UserEntity;
import com.gcu.model.LoginModel;
import com.gcu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Service layer
@Service
public class LoginService {

	@Autowired
    	private UserRepository userRepository;

	/**
	 * Authenticates the user's login credentials
	 * Finds the user matching the username and validates the password matches
	 * @param loginModel The input login model including the username and password
	 * @return True if valid, false if invalid
	 */
	public boolean checkCredentials(LoginModel loginModel)
	{
		UserEntity userEntity = new UserEntity();
		userEntity = userRepository.findByUsernameIgnoreCase(loginModel.getUsername());

		return userEntity != null && userEntity.getPassword().equals(loginModel.getPassword());
	}
}
