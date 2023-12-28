package com.OrderApi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Discounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer discountId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Products products;
	
	//10% 20%
	private Double discount;
	
	//price after discount
	//private Long discountPrice;
	
	//blackFriday etc
	private String discountName;

	public Discounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Discounts( Products products, Double discount,  String discountName) {
		super();
		this.products = products;
		this.discount = discount;
		this.discountName = discountName;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

//	public Long getDiscountPrice() {
//		return discountPrice;
//	}

//	public void setDiscountPrice(Long discountPrice) {
//		this.discountPrice = discountPrice;
//	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	
	
	
	

}
