package com.OrderApi.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.DTO.CartItemDTO;
import com.OrderApi.DTO.CartsDTO;
import com.OrderApi.DTO.UsersDTO;
import com.OrderApi.entities.CartItem;
import com.OrderApi.entities.Carts;
import com.OrderApi.entities.Products;
import com.OrderApi.entities.Users;
import com.OrderApi.repository.CartItemRepository;
import com.OrderApi.repository.CartRepository;
import com.OrderApi.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
// convert cart to cartDTO	
	public CartsDTO convertToDto(Long user_id) {
		Carts cart = getCartByUserId(user_id);
		CartsDTO tempCartsDTO = new CartsDTO();
		tempCartsDTO.setUsersDTO(userService.convertDto(userService.getUserById(user_id)));
	    tempCartsDTO.setTotal(getTotal(user_id)); 
		tempCartsDTO.setCartItems(getAllCartItemDTOsByUserId(user_id));
		return tempCartsDTO;	
	}
	
//	public CartItemDTO convertCartItemToDto(CartItem cartItem) {		
//		return new CartItemDTO(productService.convertProductsToDto(cartItem.getProduct()),cartItem.getProduct_qty());	
//	}
	
	public static List<CartItemDTO> convertCartItemListToDTOList(List<CartItem> cartItems){
		List<CartItemDTO> response = new ArrayList<>();
		for(CartItem x : cartItems) {
			response.add(new CartItemDTO(x));	
		}
		return response;
	}
	
	
//create cart for user using user_id
    public Carts createCartForUser(Long id) {
        Users user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        Carts cart = new Carts();
        cart.setUser(user);
        return cartRepo.save(cart);
    }
    
    
    
 //get cart by user-id
    public Carts getCartByUserId(Long user_id) {
    	return cartRepo.findCartByUserId(user_id);
    }
    
    
    
 //add products into cart by product_id,cart_id
    public CartItem addtoCartItem(Long cartId, Integer productId, Long productQty) {
		
		Carts cart = cartRepo.findById(cartId).get();
		if (!cartRepo.existsById(cartId)) {
			return null;	
		}
		Products product = productService.getProductById(productId);
		if (!productRepo.existsById(productId)) {
			return null;
		}
		CartItem currentCartItem = checkForExistingProduct(productId, cartId);
		if(currentCartItem == null) {
			updateQty(product, productQty);
	    	currentCartItem = new CartItem(cart, product, productQty);
			return cartItemRepo.save(currentCartItem);
		}
		else {
			updateQty(product, productQty);
			currentCartItem.setProduct_qty(currentCartItem.getProduct_qty() + productQty);
			CartItem modifiedCartItem = cartItemRepo.save(currentCartItem);
			return cartItemRepo.save(currentCartItem);
			
		}
//		else {
//			updateQty(currentCartProduct, productQty);
//		}
//    	updateQty(product, productQty);
		
//    	CartItem currentCartItem = new CartItem();
//		currentCartItem.setCart(cart);
//		currentCartItem.setProduct(product);
//		currentCartItem.setProduct_qty(productQty);
	}
    
    
 //CHECK FOR EXISTING PRODUCT
    private CartItem checkForExistingProduct(Integer productId, Long cartId) {
    	return cartItemRepo.findCartItemByCartIdAndProductId(cartId, productId);
    }
    
    
    
 //UPDATE QUANTITY
    private Long updateQty(Products product, Long qty) {
    	Long productQty = product.getQuantity();
    	if(qty!= null) {
    		Long updatedQty = productQty - qty;
    		product.setQuantity(updatedQty);
        	return product.getQuantity();
    	}
    	return null;
    }
    
    
    
 // cartitems list based on user-id
    public List<CartItem> getAllCartItemsByUserId(Long user_id){
    	List<CartItem> CartList = cartItemRepo.findAll();
    	List<CartItem> response = new ArrayList<>();
    	for(CartItem x : CartList) {
    		if (x.getCart()!=null) {
    		if(x.getCart().getUser().getId() == user_id) {
    			response.add(x);
    		}
    	}
    	}
    	getTotal(user_id);
    	return response;
    }
    
//    
    public List<CartItemDTO> getAllCartItemDTOsByUserId(Long user_id){
    	List<CartItem> CartList = cartItemRepo.findAll();
    	List<CartItemDTO> response = new ArrayList<>();
    	for(CartItem x : CartList) {
    		if (x.getCart()!=null) {
				
    		if(x.getCart().getUser().getId() == user_id) {
    			response.add(new CartItemDTO(x));		
    	}
    		}
    	}
    	getTotal(user_id);
    	return response; 	
    }
    
    
    
    
 //cartitems list based on cart-id
    public List<CartItem> getAllCartItemsById(Long cart_id){
	List<CartItem> CartList = cartItemRepo.findAll();
	List<CartItem> response = new ArrayList<>();
	for(CartItem x : CartList) {
		if (x.getCart()!=null) {
		if(x.getCart().getId() == cart_id) {
			response.add(x);
		}
		}
	}
	return response;
    }
    
    
    
    
 // cart items total based on user_id
    public Double getTotal(Long user_id) {
    	Carts currentCart = getCartByUserId(user_id);
    	List<CartItem> cartitems = getAllCartItemsById( currentCart.getId() );
    	Double total = 0.0;
    	if(cartitems != null) {
    	for(CartItem i : cartitems) {
    		total += i.getProduct().getPrice() * i.getProduct_qty();
    	}
    	}
    	currentCart.setTotal(total);
    	return total;
    }	
    
    
    
  // remove all cartitems from cart
    public Carts removeAllCartItemsByCartId(Long user_id) {
    	Carts currentCarts = getCartByUserId(user_id);
    	List<CartItem> tempCartItems = getAllCartItemsByUserId(user_id);
    	for (int i = tempCartItems.size() - 1; i >= 0; i--) {
    		cartItemRepo.deleteByCartId(tempCartItems.get(i).getCart().getId());
    		tempCartItems.remove(i);
    	}
    	currentCarts.setTotal(getTotal(user_id));
    	return currentCarts;
    }
//    
//   //remove cartitem by productname
    public Carts removeCartItemByProductName(Long user_id, String product_name) {
    	Carts currentCart = getCartByUserId(user_id);
    	List<CartItem> tempCartItems = getAllCartItemsByUserId(user_id);
    	for(int i = tempCartItems.size() - 1; i >= 0; i--) {
    		if (tempCartItems.get(i).getProduct().getName().equalsIgnoreCase(product_name)) {
    			cartItemRepo.deleteById(tempCartItems.get(i).getId());
    			tempCartItems.remove(i);			
			}
    	}
    	currentCart.setTotal(getTotal(user_id));
    	return currentCart;
    }
    
    public void clearCartFromCartItemList(List<CartItem> cartItemsList) {
    	for(int i = cartItemsList.size() - 1; i >= 0; i--) {
			cartItemsList.get(i).setCart(null);
			cartItemRepo.save(cartItemsList.get(i));
		}
    	
    }
}
    
    










//    	Carts cart = cartRepo.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
//    	
//    	Products product = productService.getProductById(productId);
//    	if (product == null) {
//            throw new RuntimeException("Product not found with ID: " + productId);
//        }
////    	updateQty(product, product_qty);
//    	cart.getProducts().add(product);
//    	
//    	return cartRepo.save(cart);
////    	CartItem cartItem = new CartItem();
////    	cartItem.setCart(tempCart);
////    	cartItem.setProduct(product);
////    	cartItem.setItemQuantity(itemQuantity);
////    	
////    	tempCart.getCartItems().add(cartItem);
////		return cartRepo.save(tempCart);
//    }

//    public List<CartItem> getAllCartItemsById(Long cart_id){
//    	List<CartItem> CartList = cartItemRepo.findAll();
//    	List<CartItem> response = new ArrayList<>();
//    	for(CartItem x : CartList) {
//    		if(x.getCart().getId() == cart_id) {
//    			response.add(x);
//    		}
//    	}
//    	return response;
//    }