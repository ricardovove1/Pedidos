package com.ma.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ma.pedidos.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	public Product findByIdAndDeleteDateIsNull(String id);
	
	public List<Product> findByDeleteDateIsNull();
	
	public boolean existsByIdAndDeleteDateIsNull(String id);
	
}
