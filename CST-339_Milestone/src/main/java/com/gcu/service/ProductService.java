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
     * Adds a product to the database
     * Currently hard-coded to only output the information about the product
     * @param productModel The product to add to the database
     * @return True if the product was added to the database, false otherwise
     */
    public boolean addProduct(ProductModel productModel) {
		System.out.println(String.format("Product submitted: BookName=%s, ISBN=%s, AuthorName=%s, Stock=%s",
				productModel.getBookName(), productModel.getIsbn(), productModel.getAuthorName(), productModel.getStock()));
		
		if (repository.findByIsbn(productModel.getIsbn()) != null)
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
    
    /**
     * Attempts to find a book by ISBN
     * @param isbn The ISBN to search for
     * @return The located ProductModel, or null if not found
     */
    public ProductModel getBookByIsbn(String isbn)
    {
    	ProductEntity entity = repository.findByIsbn(isbn);
    	if (entity != null)
    		return new ProductModel(repository.findByIsbn(isbn));
    	return null;
    }


	/**
	 * Updates an existing product.
	 * @param productModel The product model with updated information
	 */
	public void updateProduct(ProductModel productModel) {
		ProductEntity productEntity = repository.findByIsbn(productModel.getIsbn());
		if (productEntity != null) {
			// Updating product fields
			productEntity.setBookName(productModel.getBookName());
			productEntity.setAuthorName(productModel.getAuthorName());
			productEntity.setStock(productModel.getStock());

			// Saving
			repository.save(productEntity);
		}
	}
}
