package com.gcu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.utils.SessionLibrary;

@Controller
@RequestMapping("")
public class HomeController {

	
	/**
	 * Root function accessed at "{address}"
	 * @return Directs to the index.html page
	 */
	@GetMapping(value= { "", "/" })
	public String getIndex(Model model, Authentication authentication)
	{
		model.addAttribute("title", "Home Page");
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
		return "index";
	}
}
