package com.gcu.controller;

import com.gcu.model.LoginModel;
import com.gcu.service.LoginService;
import com.gcu.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ProductService productService;

	/**
	 * Login form is accessed at "{address}/login"
	 * @param model Object used on returned page
	 * @return login.html
	 */
	@GetMapping("")
	public String getIndex(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		
		return "login";
	}

	/**
	 * Posts made to "{address}/doLogin" route here
	 * @param loginModel The input loginModel from the form
	 * @param bindingResult The result of the validation
	 * @param model Object used on returned page
	 * @return login.html upon failed login, index.html upon successful login
	 */
	@PostMapping("doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// Console output for verification
		System.out.println(String.format("Login submitted with: Username=%s & Password=%s", loginModel.getUsername(), loginModel.getPassword()));
		
		// Perform validation
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			model.addAttribute("username", null);
			model.addAttribute("loginModel", loginModel);
			
			return "login";
		}
		
		// Perform authentication
		if (!loginService.checkCredentials(loginModel))
		{
			model.addAttribute("title", "Login Form");
			model.addAttribute("username", null);
			bindingResult.rejectValue("password", "error.user", "Incorrect username and/or password combination");
			
			return "login";
		}

		model.addAttribute("title", "Library");
		model.addAttribute("username", loginModel.getUsername());
		model.addAttribute("library", productService.getBooks());
		return "library";
	}
}
