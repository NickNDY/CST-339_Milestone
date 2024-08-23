package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.entity.UserEntity;
import com.gcu.repository.UserRepository;

//Service layer
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(String.format("Looking for user: %s", username));
		
		UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username);
		if (userEntity == null) {
			System.out.println(String.format("User not found: %s", username));
			throw new UsernameNotFoundException("User not found");
		}
		
		System.out.println(String.format("Found user: %s", username));

		return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername())
				.password(userEntity.getPassword())
				.authorities("Admin")    //--->can be changed to any other user
				.build();
	}
}
