package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.RegistrationModel;
import com.gcu.service.RegistrationDataService;
import com.gcu.utils.SessionState;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
    private RegistrationDataService registrationDataService;
	
	@Autowired
	private SessionState state;

	/**
	 * Registration form is accessed at "{address}/register"
	 * @param model Object used on returned page
	 * @return register.html
	 */
	@GetMapping("")
	public String getIndex(Model model)
	{
		model.addAttribute("title", "Registration Form");
		model.addAttribute("registrationModel", new RegistrationModel());
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		
		return "register";
	}

	/**
	 * Posts made to "{address}/doRegistration" route here
	 * @param registrationModel The input registrationModel from the form
	 * @param bindingResult The result of the validation
	 * @param model Object used on returned page
	 * @return register.html upon failed login, library.html upon successful login
	 */
	@PostMapping("/doRegistration")
	public String doRegistration(@Valid RegistrationModel registrationModel, BindingResult bindingResult, Model model)
	{
		// Console output for verification
		System.out.println(String.format("Registration submitted with: Address=%s, City=%s, Email=%s, First Name=%s, Last Name=%s, Password=%s, Phone=%s, State=%s, Username=%s, & Zip Code=%s",
							registrationModel.getAddress(), registrationModel.getCity(), registrationModel.getEmail(), registrationModel.getFirstname(), registrationModel.getLastname(),
							registrationModel.getPassword(), registrationModel.getPhone(), registrationModel.getState(), registrationModel.getUsername(), registrationModel.getZipcode()));
		
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		
		// Perform validation
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Registration Form");
			
			return "register";
		}
		
		// Perform unique user verification
		boolean takenUsername = registrationDataService.isUsernameTaken(registrationModel.getUsername()),
				takenEmail = registrationDataService.isEmailUsed(registrationModel.getEmail());
		if (takenUsername || takenEmail)
		{
			model.addAttribute("title", "Registration Form");
			if (takenUsername)
				bindingResult.rejectValue("username", "error.user", "Username already taken");
			if (takenEmail)
				bindingResult.rejectValue("email", "error.user", "Email already taken");
			
			return "register";
		}
		
		// Adding user to persistent data using JPA Repository
		if (!registrationDataService.createUser(registrationModel)) {
            model.addAttribute("title", "Registration Form");
            bindingResult.rejectValue("username", "error.user", "Username or email already exists");
            
            return "register";
        }

		state.setUsername("");
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
}
