package com.OrderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApi.entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
	Long countByStatus(String status);
}
