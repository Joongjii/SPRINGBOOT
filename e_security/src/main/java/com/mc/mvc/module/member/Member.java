package com.mc.mvc.module.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.mvc.module.member.dto.request.SignUpRequest;

import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@DynamicInsert //인서트쿼리를 생성할때 null인 필드는 쿼리에서 생략
@DynamicUpdate //엔티티에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Member {
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime rentableDate;
	
	public static Member createMember(SignUpRequest dto) {
		return Member.builder()
				.userId(dto.getUserId())
				.password(dto.getPassword())
				.tell(dto.getTell())
				.email(dto.getEmail())
				.grade(dto.getGrade())
				.build();
	}

	
	
	
	
	
	
	
	
	
	
	
//	public void setPassword(String password) {
//		if(password.isBlank()) throw new RuntimeException("비번 공백 불가능");
//	}
	
	
	
	
}
