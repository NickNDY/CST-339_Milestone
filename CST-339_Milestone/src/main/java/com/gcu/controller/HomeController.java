package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.utils.SessionState;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	private SessionState state;
	
	/**
	 * Root function accessed at "{address}"
	 * @return Directs to the index.html page
	 */
	@GetMapping("")
	public String getIndex(Model model)
	{
		model.addAttribute("title", "Home Page");
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		
		return "index";
	}
}
