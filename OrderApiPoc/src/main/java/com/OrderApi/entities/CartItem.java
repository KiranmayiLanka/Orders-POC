package com.OrderApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
    private Carts cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	Products product;
	
	private Long product_qty;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Carts cart, Products product, Long product_qty) {
		super();
		this.cart = cart;
		this.product = product;
		this.product_qty = product_qty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carts getCart() {
		return cart;
	}

	public void setCart(Carts cart) {
		this.cart = cart;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Long getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(Long product_qty) {
		this.product_qty = product_qty;
	}
	
	

	
	

}
