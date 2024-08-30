package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

@RestController
@RequestMapping("/service")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    /**
     * API to get all books in the library.
     * @return List of ProductModel representing all books.
     */
    @GetMapping ("/books")
    public ResponseEntity<?> getAllBooks()
    {
    	return new ResponseEntity<>(productService.getBooks(), HttpStatus.OK);
    }

    /**
     * API to get a specific book by giving ISBN of the book.
     * @param isbn The ISBN of the book to retrieve.
     * @return ProductModel representing the book or null if not found.
     */
    @GetMapping ("/{isbn}")
    public ResponseEntity<?> getSpecificBook(@PathVariable String isbn)
    {
		ProductModel book = productService.getBookByIsbn(isbn);
		
		if (book == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(book, HttpStatus.OK);
    }


}
