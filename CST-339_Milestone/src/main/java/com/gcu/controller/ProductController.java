package com.gcu.controller;

import com.gcu.model.LoginModel;
import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/library")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Library is accessed at "{address}/library"
	 * @param model Object used on returned page
	 * @return library.html
	 */
	@GetMapping("")
	public String getLibrary(Model model)
	{
		model.addAttribute("title", "Library");
		
		return "library";
	}
	
	@GetMapping("create")
	public String createProduct(Model model) {
		model.addAttribute("title", "Create a new Book");
		model.addAttribute("productModel", new ProductModel());
		return "createnewbook";
	}


	@PostMapping("create")
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Create a new Book");
			model.addAttribute("productModel", productModel);
			return "createnewbook";
		}
		
		productService.addProduct(productModel);

		model.addAttribute("title", "Library");
		// Perhaps we can highlight the added book in the library later
		// model.addAttribute("bookName", productModel.getBookName());
		return "library";
	}
}
