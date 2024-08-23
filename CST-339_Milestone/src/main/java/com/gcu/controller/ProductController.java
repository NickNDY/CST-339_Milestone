package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import com.gcu.utils.SessionLibrary;

import jakarta.validation.Valid;



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
	@GetMapping(value= { "", "/" })
	public String getLibrary(Model model, Authentication authentication)
	{
		model.addAttribute("title", "Library");
		model.addAttribute("library", productService.getBooks());
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
		return "library";
	}
	
	/**
	 * Create a book module allowing the user to submit a book
	 * GET Request returns the page
	 * @param model Object used on returned page
	 * @return createnewbook.html
	 */
	@GetMapping("create")
	public String createProduct(Model model, Authentication authentication) {
		model.addAttribute("title", "Create a new Book");
		model.addAttribute("productModel", new ProductModel());
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
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
	public String createProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model, Authentication authentication) {
		
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Create a new Book");
			model.addAttribute("productModel", productModel);
			model.addAttribute("username", SessionLibrary.getUsername(authentication));

			return "createnewbook";
		}
		
		if (productService.getBookByIsbn(productModel.getIsbn()) != null)
		{
			model.addAttribute("title", "Create a new Book");
			model.addAttribute("productModel", productModel);
			model.addAttribute("username", SessionLibrary.getUsername(authentication));
			
			bindingResult.rejectValue("isbn", "error.user", "ISBN already taken");

			return "createnewbook";
		}
		
		productService.addProduct(productModel);

		// Perhaps we can highlight the added book in the library later
		// model.addAttribute("bookName", productModel.getBookName());
		return "redirect:/Service/library";
	}
	
	/**
	 * Returns a page containing the selected book
	 * @param isbn The ISBN of the selected book
	 * @param model Object used on returned page
	 * @return viewbook.html if it succeeded, error.html if it failed
	 */
	@GetMapping("view/{isbn}")
	public String viewProduct(@PathVariable String isbn, Model model, Authentication authentication) {
    	ProductModel book = productService.getBookByIsbn(isbn);

		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
    	if (book == null)
    	{
        	model.addAttribute("errorMessage", "Book not found");
        	
        	return "error";
    	}

    	model.addAttribute("title", "View Book");
    	model.addAttribute("book", book);
    	
    	return "viewbook";
	}

	/**
	 * Returns a page for updating the selected book
	 * @param isbn The ISBN of the selected book
	 * @param model Object used on returned page
	 * @return updatebook.html if it succeeded, error.html if it failed
	 */
	@GetMapping("update/{isbn}")
	public String updateProductForm(@PathVariable("isbn") String isbn, Model model, Authentication authentication) {

		// taking product details
		ProductModel productModel = productService.getBookByIsbn(isbn);
		
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		if (productModel == null)
		{
			// if the product is not available
        	model.addAttribute("errorMessage", "Book not found");
			
			return "error";
		}
		model.addAttribute("title", "Update Book");
		model.addAttribute("productModel", productModel);
		
		return "updatebook";
	}

	/**
	 * Processes updating a product
	 * @param productModel The product to update
	 * @param bindingResult The validation results of the form
	 * @param model Object used on returned page
	 * @return library.html if successful, updatebook.html if failed
	 */
	@PostMapping("update")
	public String updateProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model, Authentication authentication)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Update Book");
			model.addAttribute("productModel", productModel);
			model.addAttribute("username", SessionLibrary.getUsername(authentication));
			
			return "updatebook"; // Return to update form if validation errors exist
		}

		productService.updateProduct(productModel);

		return "redirect:/Service/library";
	}

	/**
	 * Returns a page confirming deletion of the selected book
	 * @param isbn The ISBN of the selected book
	 * @param model Object used on returned page
	 * @return library.html if book not found, bookdelete.html if found
	 */
	@GetMapping("/delete/{isbn}")
	public String showDeleteForm(@PathVariable String isbn, Model model, Authentication authentication)
	{
		ProductModel productModel = productService.getBookByIsbn(isbn);
		if (productModel == null)
			return "redirect:/Service/library";
		
		model.addAttribute("title", "Delete Book");
		model.addAttribute("productModel", productModel);
		model.addAttribute("username", SessionLibrary.getUsername(authentication));
		
		return "bookdelete";
	}

	/**
	 * Processes deleting a product
	 * @param isbn The ISBN of the selected product
	 * @param model Object used on returned page
	 * @return library.html
	 */
	@PostMapping("/delete/{isbn}")
	public String deleteProduct(@PathVariable String isbn, Model model)
	{
		productService.deleteProduct(isbn);
		
		return "redirect:/Service/library";
	}
}