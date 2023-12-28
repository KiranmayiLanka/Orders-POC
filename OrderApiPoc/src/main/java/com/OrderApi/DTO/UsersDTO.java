package com.OrderApi.DTO;

import com.OrderApi.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsersDTO {
	
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;

	public UsersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsersDTO(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public UsersDTO(Users user) {
		super();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
