package com.OrderApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApi.Service.ProductService;
import com.OrderApi.entities.Products;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/allproducts")
	public List<Products> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/addproduct")
	public Products addProducts(@RequestBody Products products) {
		return productService.addNewProduct(products);
	}
	
	@GetMapping("/findproductbyId/{Id}")
	public ResponseEntity<Products> getProductById(@PathVariable Integer Id){
		Products products = productService.getProductById(Id);
		return ResponseEntity.ok(products);
	}
	
	@DeleteMapping("/deleteProductById/{Id}")
		public String deleteProductById(@PathVariable Integer Id) {
		return productService.deleteById(Id);
	}
	
	@GetMapping("/soldoutProducts")
	    public Integer productCount() {
		return productService.productCount();
	}
	
	@PostMapping("/orderById/{Id}")
	    public Products sellOrderById(@PathVariable Integer Id, @RequestParam Long Quantity) {
		return productService.sellOrderById(Id, Quantity);
	}
	
	@GetMapping("/countByStatus")
	public Long countByStatus(@RequestParam String status) {
		return productService.countByStatus(status);
	}
	

}
