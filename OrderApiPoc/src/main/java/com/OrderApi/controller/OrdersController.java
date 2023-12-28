package com.OrderApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApi.DTO.OrdersDTO;
import com.OrderApi.Service.OrdersService;
import com.OrderApi.entities.Orders;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	OrdersService ordersService;
	
	@PostMapping("/place-order/{user_id}")
	public OrdersDTO placeOrder(@PathVariable Long user_id,@RequestParam String address) {
		return ordersService.placeOrderByUserId(user_id,address);
	}
	
	@GetMapping("/all-orders")
	public List<OrdersDTO> getAllOrders(){
		return ordersService.getAllOrders();
	}
	
	@GetMapping("/all-orders-by-user/{user_id}")
	public List<OrdersDTO> getAllOrdersByUser(@PathVariable Long user_id){
		return ordersService.getAllOrdersByUserId(user_id);
	}
	
	@PutMapping("/update-order-status/{order_id}")
	public OrdersDTO updateOrderStatus(@PathVariable Long order_id)	{
		return ordersService.updateOrderById(order_id);
	}

}
