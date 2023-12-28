package com.OrderApi.DTO;

import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Products;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CartItemDTO {
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	ProductDTO product;
	
	private Long product_qty;

	public CartItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CartItemDTO(ProductDTO product, Long product_qty) {
		super();
		this.product = product;
		this.product_qty = product_qty;
	}
	
	public CartItemDTO(CartItem cartItem) {
		super();
		this.product = new ProductDTO(cartItem.getProduct());
		this.product_qty = cartItem.getProduct_qty();
	}


	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public Long getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(Long product_qty) {
		this.product_qty = product_qty;
	}
	
	

}
