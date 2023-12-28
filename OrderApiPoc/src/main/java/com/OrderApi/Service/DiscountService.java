package com.OrderApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.entities.Discounts;
import com.OrderApi.entities.Products;
import com.OrderApi.repository.DiscountRepository;

@Service
public class DiscountService {
	@Autowired
	DiscountRepository discountRepo;
	
	@Autowired
	ProductService productService;
	
	//add discount
	public Discounts createDiscountbyId(Integer id, Double discount) {
		
		Products currentProduct = productService.getProductById(id);
		if (currentProduct != null && currentProduct.getStatus() == "sold out") {
		Discounts tempDiscounts = discountRepo.getByProducts(currentProduct);
		if (tempDiscounts == null) {
			tempDiscounts = new Discounts(currentProduct,discount,null);
		}
		else {
			tempDiscounts.setDiscount(discount);
			tempDiscounts.setDiscountName(null);
		}
		updateDiscountPrice(currentProduct, discount);
		return discountRepo.save(tempDiscounts);
		}
		return null;
	}
	
	//update discount price after discount
	private void updateDiscountPrice(Products product, Double discount) {
		Double productPrice = product.getPrice();
		if (discount != null) {
			Double discountPrice = productPrice*(discount/100);
			product.setPrice(productPrice-discountPrice);	
		}
	}
	
	//remove discount
	public Discounts removeDiscountsById(Integer id) {
		Products currentProduct = productService.getProductById(id);
		if (currentProduct == null) {
			return null;	
		}
		Discounts currentDiscount = discountRepo.getByProducts(currentProduct);
		if (currentDiscount == null) {
//		  currentDiscount = new Discounts(currentProduct,null,null);
		}
		else {
			Double productPrice = currentProduct.getPrice();
			currentProduct.setPrice(productPrice / (1-(currentDiscount.getDiscount()/100.0)));
			currentDiscount.setDiscount(null);
			currentDiscount.setDiscountName(null);
		}
		return discountRepo.save(currentDiscount);
	}
	
	enum offers{
		CHRISTMAS_SALE(30),
		BLACK_FRIDAY_SALE(50);

		private Integer offerPercentage;
		offers(Integer offerPercentage) {
			// TODO Auto-generated constructor stub
			this.offerPercentage = offerPercentage;
		}
		
	    public static Integer getPercentage(String offerName) {
			offers x = valueOf(offerName.toUpperCase().replace(" ", "_"));
			return x.offerPercentage;
		}
	}
	
	public Discounts FestivalOfferbyId(Integer id, String offer) {
		
		Products currentProduct = productService.getProductById(id);
		if (currentProduct != null) {
		Discounts tempDiscounts = discountRepo.getByProducts(currentProduct);
		if (tempDiscounts == null) {
			tempDiscounts = new Discounts(currentProduct,null,offer);
		}
		else {
			tempDiscounts.setDiscount(null);
			tempDiscounts.setDiscountName(offer);
		}
		updateOfferPrice(currentProduct, offer);
		return discountRepo.save(tempDiscounts);
		}
		return null;
	}
	
	private void updateOfferPrice(Products product, String offer) {
		Double productPrice = product.getPrice();
		if (offers.getPercentage(offer) != null) {
			  Double offerPrice = productPrice*(offers.getPercentage(offer)/100);
			  product.setPrice(productPrice - offerPrice);	
		}
	}
	
	

}
