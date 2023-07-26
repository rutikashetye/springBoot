package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
 
	@Autowired
	MailSender sender;
	
	public void sendEmail(String email,String text,String subject) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(email);
		message.setFrom("shopperspaark@hotmail.com");
		message.setSubject(subject);
		message.setText(text);
		sender.send(message);
	}
}
