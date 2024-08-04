package com.gcu.entity;

import com.gcu.model.ProductModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String bookName;
    private String isbn;
    private String authorName;
    private Integer stock;

	public ProductEntity(Long id, String bookName, String isbn, String authorName, Integer stock)
	{
		this.id = id;
		this.bookName = bookName;
		this.isbn = isbn;
		this.authorName = authorName;
		this.stock = stock;
	}
	
	public ProductEntity()
	{
		this.id = 0L;
		this.bookName = "None";
		this.isbn = "012345678912345";
		this.authorName = "John Doe";
		this.stock = 0;
	}
	
	public ProductEntity(ProductModel model)
	{
		this.bookName = model.getBookName();
		this.isbn = model.getIsbn();
		this.authorName = model.getAuthorName();
		this.stock = model.getStock();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
