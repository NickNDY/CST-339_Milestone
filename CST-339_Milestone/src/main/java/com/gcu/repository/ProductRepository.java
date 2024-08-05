package com.gcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	ProductEntity findByIsbn(String isbn);
}
