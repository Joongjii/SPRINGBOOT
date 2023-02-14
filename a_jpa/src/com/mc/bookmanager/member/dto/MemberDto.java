package com.mc.bookmanager.member.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.bookmanager.member.Member;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDto {

	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	private Boolean isLeave;
	private LocalDateTime regDate;
	private LocalDateTime rentableDate;
	
	public MemberDto(Member member) {
		this.userId= member.getUserId();
		this.password = member.getPassword();
		this.email = member.getEmail();
		this.tell = member.getTell();
		this.isLeave = member.getIsLeave();
		this.regDate = member.getRegDate();
		this.rentableDate = member.getRentableDate();
		
	}
	
	public static List<MemberDto> toDoList(List<Member> entityList){
		return entityList.stream().map(e-> new MemberDto(e)).collect(Collectors.toList());
	}
	
	
	
	
	
//	public static MemberDto toDto(Member entity) {
//		MemberDto member = new MemberDto();	
//		member.setUserId(entity.getUserId());
//		}
	
	
	
	
//	public Member toEntity() {
//		Member member = new Member();
//		
//	}
	
	
	
	
}
