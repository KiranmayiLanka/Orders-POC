package com.OrderApi.DTO;



import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class CartsDTO {
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private UsersDTO usersDTO;
    
    private Double Total;
    
    private List<CartItemDTO> cartItems;

	public CartsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartsDTO(UsersDTO usersDTO, Double total, List<CartItemDTO> cartItems ) {
		super();
		this.usersDTO = usersDTO;
		Total = total;
		this.cartItems = cartItems;
		
	}

	public UsersDTO getUsersDTO() {
		return usersDTO;
	}

	public void setUsersDTO(UsersDTO usersDTO) {
		this.usersDTO = usersDTO;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		this.Total = total;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}
	
	
    
    

}
