package com.OrderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApi.entities.Carts;

@Repository
public interface CartRepository extends JpaRepository<Carts, Long> {
	
	public Carts findCartByUserId(Long user_id);

}
