package com.OrderApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApi.DTO.UsersDTO;
import com.OrderApi.Service.UserService;
import com.OrderApi.entities.Users;

@RestController
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/allUsers")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/addUser")
	public Users addNewUser(@RequestBody Users user) {
		return userService.registerUsers(user);
	}
	
	@GetMapping("/getUserById/{id}")
	public Users getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/getUserDTOById/{id}")
	public UsersDTO getUsersDTOById(@PathVariable Long id) {
		UsersDTO tempUsersDTO = userService.convertDto(userService.getUserById(id));
		return tempUsersDTO;
	}
	
	@DeleteMapping ("/deleteUserById/{id}")
	public void  deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
		
	

}
