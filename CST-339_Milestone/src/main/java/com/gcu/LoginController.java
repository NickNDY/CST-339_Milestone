package com.gcu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("")
public class LoginController {

	/**
	 * Login form is accessed at "{address}/login"
	 * @param model Object used on returned page
	 * @return login.html
	 */
	@GetMapping("/login")
	public String getIndex(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		
		return "login";
	}

	/**
	 * Posts made to "{address}/login" route here
	 * @param loginModel The input loginModel from the form
	 * @param bindingResult The result of the validation
	 * @param model Object used on returned page
	 * @return login.html upon failed login, index.html upon successful login
	 */
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// Perform validation
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			model.addAttribute("username", null);
			
			return "login";
		}
		
		// Perform authentication
		if (!loginModel.getPassword().equals("password"))
		{
			model.addAttribute("title", "Login Form");
			model.addAttribute("username", null);
			bindingResult.rejectValue("password", "error.user", "Incorrect username and/or password combination");
			
			return "login";
		}
		
		model.addAttribute("username", loginModel.getUsername());
		
		return "index";
	}
}
