package com.OrderApi.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.DTO.CartItemDTO;
import com.OrderApi.DTO.OrdersDTO;
import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Carts;
import com.OrderApi.entities.Orders;
import com.OrderApi.entities.Orders.order_status;
import com.OrderApi.repository.OrdersRepository;

@Service
public class OrdersService {
	
	@Autowired
	OrdersRepository ordersRepo;
	
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	public OrdersDTO placeOrderByUserId(Long user_id,String address) {
		Carts currentCart = cartService.getCartByUserId(user_id);
		if (currentCart == null) {
			
			return null;
		}
		List<CartItem> cartItemsList = cartService.getAllCartItemsById(currentCart.getId());
		if (cartItemsList == null) {
//			cartService.addtoCartItem(user_id, product_id, product_qty);
			return null;
		}
		Orders order = new Orders();
		order.setUser(userService.getUserById(user_id));
		order.setAddress(address);
		order.setOrder_items(cartItemsList);
		order.setStatus(order_status.ORDER_PLACED);
		ordersRepo.save(order);
		emailService.sendSimpleMessage(user_id, null, "Order Confirmation" , "Your Order has been placed!");
			
		cartService.clearCartFromCartItemList(cartItemsList);
		return new OrdersDTO(order);
	}
	
	public List<OrdersDTO> convertOrdersListToDTOList(List<Orders> orders){
		List<OrdersDTO> response = new ArrayList<>();
		for(Orders x : orders) {
			response.add(new OrdersDTO(x));	
		}
		return response;
	}
	
	public List<OrdersDTO> getAllOrders(){
		return convertOrdersListToDTOList(ordersRepo.findAll());
	}
	
	public List<OrdersDTO> getAllOrdersByUserId(Long user_id) {
		List<Orders> allOrders = ordersRepo.findAll();
		List<Orders> response = new ArrayList<>();
		for(Orders x : allOrders) {
			if(x.getUser().getId() == user_id)
			response.add(x);
		}
		return convertOrdersListToDTOList(response);
			
	}
	
	public OrdersDTO updateOrderById(Long order_id) {
		Orders currentOrder = ordersRepo.findById(order_id).get();
		updateOrder(currentOrder);
		ordersRepo.save(currentOrder);
		return new OrdersDTO(currentOrder);	
	}
	
	public void updateOrder( Orders order) {
		order_status status = order.getStatus();
		switch (status) {
		case ORDER_PLACED: {
			order.setStatus(order_status.DISPATCHED);
			break;
		}
		case DISPATCHED: {
			order.setStatus(order_status.OUT_FOR_DELIVERY);
			break;
		}
		case OUT_FOR_DELIVERY: {
			order.setStatus(order_status.DELIVERED);
			break;
		}
		case DELIVERED: {
			order.setStatus(order_status.DELIVERED);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + status);
		}
	}

}
