package com.mc.mvc.module.member;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mc.mvc.Infra.code.Code;
import com.mc.mvc.Infra.util.mail.EmailSender;
import com.mc.mvc.module.member.dto.Principal;
import com.mc.mvc.module.member.dto.request.LoginRequest;
import com.mc.mvc.module.member.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final RestTemplate restTemplate;
	private final EmailSender sender;
	private final PasswordEncoder passwordEncoder;
	
	public boolean existUser(String userId) {
	      return memberRepository.existsById(userId);
	   }

	public void autheticateEmail(@Valid SignUpRequest form, String authToken) {
		
		  Map<String, Object> body = new LinkedHashMap<String, Object>();
	      body.put("userId", form.getUserId());
	      body.put("authToken", authToken);
	      body.put("mailTemplate", "signup-email-auth");
	      
	      RequestEntity<Map<String, Object>> request = 
	            RequestEntity
	            .post(Code.DOMAIN + "/mail")
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(body);
	      
	      ResponseEntity<String> response =  restTemplate.exchange(request, String.class);
	      String html = response.getBody();
	      
	      sender.send(form.getEmail(), "회원가입을 환영합니다. 링크를 클릭해 회원가입을 완료하세요.", html);
		
	}
	
	@Transactional
	public void registNewMember(SignUpRequest form) {
		
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		Member member = Member.createMember(form);
		memberRepository.save(member);
		
	}

	public Principal authenticateUser(LoginRequest loginRequest) {
	      
	      Member member = memberRepository.findByUserIdAndIsLeave(loginRequest.getUserId(), false);
	      
	      if(member == null) return null;
	      if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) return null;
	      
	      return new Principal(member);
	   }
	
	
	
	
	
	
	
	
	
}