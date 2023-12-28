package com.OrderApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApi.Service.DiscountService;
import com.OrderApi.entities.Discounts;

@RestController
@RequestMapping("/discount")
public class DiscountController {

	@Autowired
	DiscountService discountService;
	@PutMapping("/addDiscountById/{id}")
	public Discounts addDiscountById(@PathVariable Integer id ,@RequestParam Double discount) {
		return discountService.createDiscountbyId(id, discount);
	}
	
	@PutMapping("/removeDiscountById/{id}")
	public Discounts removeDiscountsById(@PathVariable Integer id) {
		return discountService.removeDiscountsById(id);
	}
	
	@PutMapping("/addFestivalOfferById/{id}")
	public Discounts addFestivalOfferByIdDiscounts(@PathVariable Integer id, @RequestParam String offer) {
		return discountService.FestivalOfferbyId(id, offer);
		
	}
	
}
