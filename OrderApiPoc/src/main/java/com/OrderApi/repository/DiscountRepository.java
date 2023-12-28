package com.OrderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApi.entities.Discounts;
import com.OrderApi.entities.Products;

@Repository
public interface DiscountRepository extends JpaRepository<Discounts, Integer> {
	
  public Discounts getByProducts(Products products);

}
