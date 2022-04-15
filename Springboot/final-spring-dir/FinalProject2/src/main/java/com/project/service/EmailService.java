package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	 @Autowired
	 private JavaMailSender emailSender;

	 public void sendEmailForNewRegistration(String email) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("ewastemgmt2022@gmail.com");
        message.setTo(email); 
        message.setSubject(" registration on E-Waste Management"); 
        message.setText("Thank you for showing interest in E-Waste anagement");
        emailSender.send(message);
	 }
}