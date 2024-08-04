package com.gcu.service;

import com.gcu.entity.ProductEntity;
import com.gcu.model.ProductModel;
import com.gcu.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
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
		
		if (repository.existsByIsbn(productModel.getIsbn()))
		{
			System.out.println(String.format("Book with isbn %s already exists", productModel.getIsbn()));
		}
		else
		{
			repository.save(new ProductEntity(productModel));
		}
		
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
    	Iterable<ProductEntity> entityList = repository.findAll();
    	List<ProductModel> modelList = new ArrayList<ProductModel>();
    	
    	for (ProductEntity entity : entityList)
    	{
    		modelList.add(new ProductModel(entity));
    	}
    	
    	return modelList;
    }
}
