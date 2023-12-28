package com.OrderApi.DTO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.Service.CartService;
import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Orders;
import com.OrderApi.entities.Users;
import com.OrderApi.entities.Orders.order_status;

import jakarta.persistence.OneToMany;


//@Service
public class OrdersDTO {
	
    private UsersDTO user;
    
	private String address;
	
	private List<CartItemDTO> order_items;
	
	private order_status status;

	public OrdersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersDTO(UsersDTO user, String address, List<CartItemDTO> order_items, order_status status) {
		super();
		this.user = user;
		this.address = address;
		this.order_items = order_items;
		this.status = status;
	}
	public OrdersDTO ( Orders order) {
		this.user = new UsersDTO(order.getUser());
		this.address = order.getAddress();
		this.order_items = CartService.convertCartItemListToDTOList(order.getOrder_items());
		this.status = order.getStatus();
		return;
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CartItemDTO> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<CartItemDTO> order_items) {
		this.order_items = order_items;
	}

	public order_status getStatus() {
		return status;
	}

	public void setStatus(order_status status) {
		this.status = status;
	}
	
	



}
