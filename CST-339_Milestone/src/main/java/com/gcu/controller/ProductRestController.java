package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;

    /**
     * API to get all books in the library.
     * @return List of ProductModel representing all books.
     */
    @GetMapping ("/books")
    public List<ProductModel> getAllBooks() {
        return productService.getBooks();
    }

    /**
     * API to get a specific book by giving ISBN of the book.
     * @param isbn The ISBN of the book to retrieve.
     * @return ProductModel representing the book or null if not found.
     */
    @GetMapping ("/{isbn}")
    public ProductModel getSpecificBook(@PathVariable String isbn){
        return productService.getBookByIsbn(isbn);
    }


}
