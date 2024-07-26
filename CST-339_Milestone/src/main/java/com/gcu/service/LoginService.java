package com.gcu.service;

import com.gcu.model.LoginModel;
import org.springframework.stereotype.Service;

//Service layer
@Service
public class LoginService {

    public boolean checkCredentials(LoginModel loginModel)
    {
        // hardcoded password, will be removed when db is connected.
        return loginModel.getPassword().equals("password");
    }
}
