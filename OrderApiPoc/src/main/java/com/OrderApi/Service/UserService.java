package com.OrderApi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderApi.DTO.UsersDTO;
import com.OrderApi.entities.Users;
import com.OrderApi.repository.CartRepository;
import com.OrderApi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EmailService emailService;
	
	public UsersDTO convertDto(Users user) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setUsername(user.getUsername());
		usersDTO.setEmail(user.getEmail());
		return usersDTO;
	}
	
	//get all the existing users
	public List<Users> getAllUsers(){
		return userRepo.findAll();	
	}
	
	//get user by id
	public Users getUserById(Long id) {
		Users currentUser = userRepo.findById(id).get();
		if (currentUser == null) {
			return null;
		}
		return currentUser;	
	}
	
	//add new user
	public Users registerUsers(Users user) {
	
		userRepo.save(user);
		emailService.sendSimpleMessage(user.getId(), null, "New Registration!!!", "Welcome to club");
		return user;
		
	}
	
	//delete a user by id
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

}


