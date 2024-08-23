package com.gcu.testing;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.utils.SessionLibrary;

@Controller
@RequestMapping("/test")
public class TestController
{
	@Autowired
	TestCases testCases;
	
	@GetMapping(value= { "", "/" })
	public String index(Model model, Authentication authentication)
	{
		model.addAttribute("title", "Tests");
		model.addAttribute("passes", new ArrayList<String>());
		model.addAttribute("fails", new ArrayList<String>());
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
		int failures = 0;
		
		failures += testCases.TestProductService(model);
		failures += testCases.TestUserService(model);
		
		model.addAttribute("failurecount", failures);
		
		return "test";
	}
}
