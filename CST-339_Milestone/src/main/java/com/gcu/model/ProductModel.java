package com.gcu.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class ProductModel {
    @NotNull(message="Book name is required")
    @Size(min=3, max=255, message="Book name must be between 3 and 255 characters")
    private String bookName;

    @NotNull(message="ISBN is required")
    @Size(min=14, max=14, message="ISBN must contain 14 characters")
    private String isbn;

    @NotNull(message="Author name is required")
    @Size(min=10, max=255, message="Author name must be between 10 and 255 characters")
    private String authorName;

    @NotNull(message="Stock is required")
    @Min(1)
    @Max(9999)
    private Integer stock;

    public ProductModel(String bookName, String isbn, String authorName, Integer stock) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.authorName = authorName;
        this.stock = stock;
    }

    public ProductModel() {

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
