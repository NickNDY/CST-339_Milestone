package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.entity.UserEntity;
import com.gcu.model.RegistrationModel;
import com.gcu.repository.UserRepository;

/**
 * Service class for handling user registration.
 */
@Service
public class RegistrationDataService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user in the persistent data store.
     * 
     * @param registrationModel The registration model containing user details.
     * @return True if the user was created successfully, false if the username or email already exists.
     */
    public boolean createUser(RegistrationModel registrationModel) {
        if (userRepository.findByUsername(registrationModel.getUsername()) != null ||
            userRepository.findByEmail(registrationModel.getEmail()) != null) {
            return false; // Username or email already exists
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registrationModel.getUsername());
        userEntity.setPassword(registrationModel.getPassword()); 
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
}
