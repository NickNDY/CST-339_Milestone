package com.gcu.service;

import com.gcu.entity.UserEntity;
import com.gcu.model.LoginModel;
import com.gcu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Service layer
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository ;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername())
				.password(userEntity.getPassword())
				.authorities("Admin")    //--->can be changed to any other user
				.build();
	}
}
