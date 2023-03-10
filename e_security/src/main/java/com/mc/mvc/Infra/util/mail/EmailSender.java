package com.mc.mvc.Infra.util.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mc.mvc.Infra.code.Code;
import com.mc.mvc.Infra.code.ErrorCode;
import com.mc.mvc.Infra.exception.HandlableException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSender {
	
	private final JavaMailSender mailSender;
	
	public void send(String to, String subject, String html) {
		
		MimeMessage msg = mailSender.createMimeMessage();
			
		try {
			
			msg.setFrom(Code.SMTP_FROM.desc);
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setContent(html, "text/html; charset=UTF-8");
			mailSender.send(msg);
			
		} catch (MessagingException e) {
			throw new HandlableException(ErrorCode.FAILED_SEND_EMAIL);
		}
		
		
			
		
		
	}
	
	
	
	
	
	
	

}
