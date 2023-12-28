package com.OrderApi.DTO;

import com.OrderApi.entities.Products;

public class ProductDTO {
	
	private String name;
	private String description;
	private String category;
	private Double price;
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(String name, String description, String category, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	
	public ProductDTO(Products product) {
		super();
		this.name = product.getName();
		this.description = product.getDescription();
		this.category = product.getCategory();
		this.price = product.getPrice();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


}
