package com.gcu.service;

import com.gcu.entity.UserEntity;
import com.gcu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads the user by username and returns a UserDetails object for authentication.
     * @param username The username of the user to load.
     * @return UserDetails object containing the user's information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username);
        
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Returning UserDetails with the user's username, password, and roles/authorities
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    /**
     * Authenticates the user's login credentials.
     * @param loginModel The input login model including the username and password.
     * @return True if valid, false if invalid.
     */
    public boolean checkCredentials(LoginModel loginModel) {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(loginModel.getUsername());

        return userEntity != null && userEntity.getPassword().equals(loginModel.getPassword());
    }
}
