package com.OrderApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApi.DTO.CartItemDTO;
import com.OrderApi.DTO.CartsDTO;
import com.OrderApi.DTO.OrdersDTO;
import com.OrderApi.Service.CartService;
import com.OrderApi.Service.UserService;
import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Carts;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	
//	@GetMapping("/{user_id}")
//	public Carts getCartsByUserId(@PathVariable Long user_id) {
//		return cartService.getCartByUserId(user_id);
//	}
	
	@GetMapping("/{user_id}")
	public CartsDTO getCartsDTOByUserId(@PathVariable Long user_id) {
		return cartService.convertToDto(user_id);
	}
	
//	@GetMapping("/get-total/{user_id}")
//	public Carts getTotal(@PathVariable Long user_id) {
//		return cartService.getTotal(user_id);
//	}
	
	@GetMapping("get-all-by-user-id/{user_id}")
	 public List<CartItem> getAllCartItemsByUserId(@PathVariable Long user_id){
		return cartService.getAllCartItemsByUserId(user_id);
	}
	
	@GetMapping("/get-all-cartitems/{user_id}")
	public List<CartItemDTO> getCartItemDTOs(@PathVariable Long user_id){
		return cartService.getAllCartItemDTOsByUserId(user_id);
	}
	
	@PostMapping("/create/{userId}")
	public Carts createCartforUser(@PathVariable Long userId){
		Carts cart = cartService.createCartForUser(userId);
		return cart;
	}
	
	
	@PostMapping("/addToCart/{cartId}/product/{productId}")
	public CartItem addToCart(@PathVariable Long cartId, @PathVariable Integer productId, @RequestParam Long product_qty) {
		CartItem cartItem = cartService.addtoCartItem(cartId, productId, product_qty);
		return cartItem;
	}
	
	@GetMapping("/get-all-by-cartId/{cart_id}")
	public List<CartItem> getAllByCartId(@PathVariable Long cart_id) {
		return cartService.getAllCartItemsById(cart_id);
	}
	
	@PutMapping("/remove-all-cartitems/{user_id}")
	public Carts removeAllCartItemsByUserId(@PathVariable Long user_id) {
		return cartService.removeAllCartItemsByCartId(user_id);
	}
	
	@PutMapping("/remove-cartitem-by-productname/{user_id}/{product_name}")
	public Carts removeItemByProductName(@PathVariable Long user_id,@PathVariable String product_name ) {
		return cartService.removeCartItemByProductName(user_id,product_name);
	}
	
	

}
