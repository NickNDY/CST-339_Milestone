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
@RequestMapping("/books")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Login form is accessed at "{address}/login"
	 * @param model Object used on returned page
	 * @return login.html
	 */
	@GetMapping("")
	public String getBooksModel(Model model)
	{
		model.addAttribute("title", "Books");
		model.addAttribute("productModel", new ProductModel());
		
		return "books";
	}


	@PostMapping("create")
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		System.out.println(String.format("Product submitted: BookName=%s, ISBN=%s, AuthorName=%s, Stock=%s",
				productModel.getBookName(), productModel.getIsbn(), productModel.getAuthorName(), productModel.getStock()));

		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Books");
			return "CreateNewBook";
		}

		model.addAttribute("title", "Library");
		model.addAttribute("bookName", productModel.getBookName());
		return "library";
	}
}
