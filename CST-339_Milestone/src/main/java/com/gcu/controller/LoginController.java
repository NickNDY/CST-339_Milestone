package com.gcu.controller;

import com.gcu.model.LoginModel;
import com.gcu.utils.SessionLibrary;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * Login form is accessed at "{address}/login"
	 * @param model Object used on returned page
	 * @return login.html
	 */
	@GetMapping("/login")
	public String getIndex(Model model, Authentication authentication)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
		return "login";
	}
}