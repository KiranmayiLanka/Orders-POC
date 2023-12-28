package com.OrderApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Carts {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
    
    private Double Total;
    
//    @ManyToMany
//    @JoinTable(
//      name = "cart_product",
//      joinColumns = @JoinColumn(name = "cart_id"),
//      inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private List<Products> products = new ArrayList<>();
    
    
    
//    private Long product_qty;

	public Carts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carts(Users user, Double total) {
		super();
		this.user = user;
		this.Total = total;
//		this.products = products;
//		this.product_qty = product_qty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}
	
	

//	public List<Products> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Products> products) {
//		this.products = products;
//	}

//	public Long getProduct_qty() {
//		return product_qty;
//	}
//
//	public void setProduct_qty(Long product_qty) {
//		this.product_qty = product_qty;
//	}
    
	
    
    
    
    


       
    

}