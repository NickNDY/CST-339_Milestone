package com.gcu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

	/**
	 * Root function accessed at "{address}"
	 * @return Directs to the index.html page
	 */
	@GetMapping("")
	public String getIndex()
	{
		return "index";
	}
}
