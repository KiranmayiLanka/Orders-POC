package com.OrderApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Products;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	void deleteByCartId(Long cart_id);
	List<CartItem> findAllByCartId(Long cart_id);
	CartItem findCartItemByCartIdAndProductId(Long cartId, Integer productId);
	
}
