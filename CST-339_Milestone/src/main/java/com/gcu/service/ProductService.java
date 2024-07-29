package com.gcu.service;

import com.gcu.model.ProductModel;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private List<ProductModel> bookList = new ArrayList<ProductModel>();
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
		bookList.add(productModel);
		
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
    	return bookList;
    }
    
    /**
     * Initializes the hard-coded list of books
     */
    @PostConstruct
    public void init()
    {
    	bookList.add(new ProductModel("Book 1", "00000000000001", "Author 1", 1));
    	bookList.add(new ProductModel("Book 2", "00000000000002", "Author 2", 2));
    	bookList.add(new ProductModel("Book 3", "00000000000003", "Author 3", 3));
    	bookList.add(new ProductModel("Book 4", "00000000000004", "Author 4", 4));
    	bookList.add(new ProductModel("Book 5", "00000000000005", "Author 5", 5));
    	bookList.add(new ProductModel("Book 6", "00000000000006", "Author 6", 6));
    	bookList.add(new ProductModel("Book 7", "00000000000007", "Author 7", 7));
    	bookList.add(new ProductModel("Book 8", "00000000000008", "Author 8", 8));
    }
}
