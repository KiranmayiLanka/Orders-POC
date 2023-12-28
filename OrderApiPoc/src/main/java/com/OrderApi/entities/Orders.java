package com.OrderApi.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@CreationTimestamp
	private LocalDateTime order_placed;
	
	@ManyToOne
	private Users user;
	
	private String address;
	
	public enum order_status{
		ORDER_PLACED ,
		DISPATCHED,
		OUT_FOR_DELIVERY,
		DELIVERED;	
	}
	
	@OneToMany
	private List<CartItem> order_items;
	
	private order_status status;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Users user, String address, List<CartItem> order_items, order_status status) {
		super();
		this.user = user;
		this.address = address;
		this.order_items = order_items;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrder_placed() {
		return order_placed;
	}

	public void setOrder_placed(LocalDateTime order_placed) {
		this.order_placed = order_placed;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CartItem> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<CartItem> order_items) {
		this.order_items = order_items;
	}

	public order_status getStatus() {
		return status;
	}

	public void setStatus(order_status status) {
		this.status = status;
	}
	
		
	
	
	
	
	

}
