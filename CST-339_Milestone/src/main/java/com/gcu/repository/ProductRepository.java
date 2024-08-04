package com.gcu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.gcu.entity.ProductEntity;

public interface ProductRepository extends Repository<ProductEntity, Long> {

	ProductEntity save(ProductEntity product);
	
	Optional<ProductEntity> findById(long id);
	
	Iterable<ProductEntity> findAll();
	
	long count();
	
	void delete(ProductEntity entity);
	
	boolean existsById(long id);
	
	boolean existsByIsbn(String isbn);
}
