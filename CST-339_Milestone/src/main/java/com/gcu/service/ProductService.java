package com.gcu.service;

import com.gcu.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    public boolean isProductAvailable(ProductModel productModel, Integer quantity) {
        return productModel.getStock() <= quantity;
    }
    
    public boolean addProduct(ProductModel productModel) {
		System.out.println(String.format("Product submitted: BookName=%s, ISBN=%s, AuthorName=%s, Stock=%s",
				productModel.getBookName(), productModel.getIsbn(), productModel.getAuthorName(), productModel.getStock()));
		
		// Hard-coded, add to database here
		
		// Return true if changes made to database, otherwise false
		return true;
    }
}
