package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import com.gcu.utils.SessionState;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/library")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SessionState state;

	/**
	 * Library is accessed at "{address}/library"
	 * @param model Object used on returned page
	 * @return library.html
	 */
	@GetMapping("")
	public String getLibrary(Model model)
	{
		model.addAttribute("title", "Library");
		model.addAttribute("library", productService.getBooks());
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		
		return "library";
	}
	
	/**
	 * Create a book module allowing the user to submit a book
	 * GET Request returns the page
	 * @param model Object used on returned page
	 * @return createnewbook.html
	 */
	@GetMapping("create")
	public String createProduct(Model model) {
		model.addAttribute("title", "Create a new Book");
		model.addAttribute("productModel", new ProductModel());
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		return "createnewbook";
	}

	/**
	 * Create a book module allowing the user to submit a book
	 * POST Requests are processed and the library is returned
	 * @param productModel The submitted book
	 * @param bindingResult The validation results
	 * @param model Object used on returned page
	 * @return createnewbook.html if it failed, library.html if it succeeded
	 */
	@PostMapping("create")
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Create a new Book");
			model.addAttribute("productModel", productModel);
			return "createnewbook";
		}
		
		if (productService.getBookByIsbn(productModel.getIsbn()) != null)
		{
			System.out.println(String.format("Book with isbn %s already exists", productModel.getIsbn()));
			model.addAttribute("title", "Create a new Book");
			model.addAttribute("productModel", productModel);
			bindingResult.rejectValue("isbn", "error.user", "ISBN already taken");
			return "createnewbook";
		}
		
		productService.addProduct(productModel);

		model.addAttribute("title", "Library");
		model.addAttribute("library", productService.getBooks());
		model.addAttribute("username", state.getUsername().length() > 0 ? state.getUsername() : null);
		// Perhaps we can highlight the added book in the library later
		// model.addAttribute("bookName", productModel.getBookName());
		return "library";
	}

	@GetMapping("update")
	public String updateProductForm(@PathVariable("isbn") String isbn, Model model) {

		// taking product details
		ProductModel productModel = productService.getBookByIsbn(isbn);
		if (productModel == null) {
			// if the product is not available
			model.addAttribute("error", "Product not found");
			return "error";
		}
		model.addAttribute("title", "Update Book");
		model.addAttribute("productModel", productModel);
		model.addAttribute("username", !state.getUsername().isEmpty() ? state.getUsername() : null);
		return "updatebook"; // This should be the name of the HTML template for updating a product
	}


	@PostMapping("update")
	public String updateProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Update Book");
			model.addAttribute("productModel", productModel);
			return "updatebook"; // Return to update form if validation errors exist
		}

		productService.updateProduct(productModel);

		model.addAttribute("title", "Library");
		model.addAttribute("library", productService.getBooks());
		model.addAttribute("username", !state.getUsername().isEmpty() ? state.getUsername() : null);
		return "library";
	}



}
