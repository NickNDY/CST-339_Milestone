package com.gcu.service;

import com.gcu.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	/**
	 * Checks whether a product is in stock
	 * @param productModel The product to check
	 * @param quantity The quantity needed
	 * @return True if there are enough, false otherwise
	 */
    public boolean isProductAvailable(ProductModel productModel, Integer quantity) {
        return productModel.getStock() <= quantity;
    }
    
    /**
     * Adds a product to the database
     * Currently hard-coded to only output the information about the product
     * @param productModel The product to add to the database
     * @return True if the product was added to the database, false otherwise
     */
    public boolean addProduct(ProductModel productModel) {
		System.out.println(String.format("Product submitted: BookName=%s, ISBN=%s, AuthorName=%s, Stock=%s",
				productModel.getBookName(), productModel.getIsbn(), productModel.getAuthorName(), productModel.getStock()));
		
		// Hard-coded, add to database here
		
		// Return true if changes made to database, otherwise false
		return true;
    }
    
    /**
     * Returns a list of all books in the database
     * Currently hard-coded to return a short list of books
     * @return The list of all books in the database
     */
    public List<ProductModel> getBooks()
    {
    	List<ProductModel> bookList = new ArrayList<ProductModel>();

    	bookList.add(new ProductModel("Book 1", "Author 1", "00000000000001", 1));
    	bookList.add(new ProductModel("Book 5", "Author 2", "00000000000002", 2));
    	bookList.add(new ProductModel("Book 6", "Author 3", "00000000000003", 3));
    	bookList.add(new ProductModel("Book 7", "Author 4", "00000000000004", 4));
    	bookList.add(new ProductModel("Book 8", "Author 5", "00000000000005", 5));
    	
    	return bookList;
    }
}
