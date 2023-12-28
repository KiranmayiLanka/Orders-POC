package com.OrderApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.OrderApi.repository.UserRepository;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private UserRepository userRepo;

    public void sendSimpleMessage(
      Long user_id, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("xampleapi@gmail.com");
        message.setTo((userRepo.findById(user_id).get()).getEmail()); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }

	

}
