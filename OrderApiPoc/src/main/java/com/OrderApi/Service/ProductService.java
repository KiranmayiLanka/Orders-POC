package com.OrderApi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.DTO.ProductDTO;
import com.OrderApi.DTO.UsersDTO;
import com.OrderApi.entities.Products;
import com.OrderApi.entities.Users;
import com.OrderApi.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	
	public ProductDTO convertProductsToDto( Products product) {
		return new ProductDTO(product.getName(),product.getDescription(),product.getCategory(),product.getPrice());
	}
	
	//add a single product
	public Products addNewProduct(Products product) {
		return productRepo.save(product);
	}
	
	//get all the products 
	public List<Products> getAllProducts(){
		return productRepo.findAll();
	}
	
	//get product by id
	public Products getProductById(Integer Id) {
		Optional<Products> currentprod = productRepo.findById(Id);
		if(currentprod.isPresent())
			return currentprod.get();
		else 
			return null;
	}
	
	//delete by id
	public String deleteById(Integer Id) {
		productRepo.deleteById(Id);
		return "product has been deleted";
	}
	
	//count of products
	public Integer productCount() {
		List<Products> products = productRepo.findAll();
		int count = 0;		
		for(Products tempproduct : products ) {
			if(tempproduct.getStatus().equalsIgnoreCase("available")) {
				count++;
			}
		}
		return count;	
	}
	
	public Long countByStatus(String Status) {
		return productRepo.countByStatus(Status);
	}
	
	//sell order by name
	public Products sellOrderById(Integer Id,Long Quantity) {
		if (!(productRepo.existsById(Id))) {
			return null;	
		}
		Products tempProduct = productRepo.findById(Id).get();
		if (tempProduct.getQuantity() >= Quantity) {
			tempProduct.setQuantity(tempProduct.getQuantity()-Quantity);
			if(tempProduct.getQuantity()== 0) {
				tempProduct.setStatus("sold out");
			}
			productRepo.save(tempProduct);
			return tempProduct;	
		}
		return null;
		
	}
	
	
	
	

}
