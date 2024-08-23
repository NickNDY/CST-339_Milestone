package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.entity.UserEntity;
import com.gcu.model.RegistrationModel;
import com.gcu.repository.UserRepository;
//import com.gcu.utils.SecurityConfig;

/**
 * Service class for handling user registration.
 */
@Service
public class RegistrationDataService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    /**
     * Creates a new user in the persistent data store.
     * 
     * @param registrationModel The registration model containing user details.
     * @return True if the user was created successfully, false if the username or email already exists.
     */
    public boolean createUser(RegistrationModel registrationModel) {
        if (userRepository.findByUsernameIgnoreCase(registrationModel.getUsername()) != null ||
            userRepository.findByEmailIgnoreCase(registrationModel.getEmail()) != null) {
            return false; // Username or email already exists
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registrationModel.getUsername());
        userEntity.setPassword(encoder.encode(registrationModel.getPassword())); 
        userEntity.setFirstname(registrationModel.getFirstname());
        userEntity.setLastname(registrationModel.getLastname());
        userEntity.setEmail(registrationModel.getEmail());
        userEntity.setPhone(registrationModel.getPhone());
        userEntity.setAddress(registrationModel.getAddress());
        userEntity.setCity(registrationModel.getCity());
        userEntity.setState(registrationModel.getState());
        userEntity.setZipcode(registrationModel.getZipcode());

        userRepository.save(userEntity);
        return true;
    }
	
	/**
	 * Checks whether a username is already taken or forbidden (admin/administrator)
	 * @param username The username to check
	 * @return True if taken, false if available
	 */
    public  boolean isUsernameTaken(String username)
    {
        return username.equalsIgnoreCase("administrator") || username.equalsIgnoreCase("admin") || userRepository.findByUsernameIgnoreCase(username) != null;
    }

    /**
     * Checks whether an email is already used
     * @param username
     * @return True if taken, false if available
     */
    public  boolean isEmailUsed(String email)
    {
        return userRepository.findByEmailIgnoreCase(email) != null;
    }
}
