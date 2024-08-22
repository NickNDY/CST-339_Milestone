package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/service")
public class RestController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/books")
	public ResponseEntity<?> getProducts()
	{
		return new ResponseEntity<>(productService.getBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/book/{isbn}")
	public ResponseEntity<?> getBook(@PathVariable String isbn)
	{
		ProductModel book = productService.getBookByIsbn(isbn);
		
		if (book == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
}
