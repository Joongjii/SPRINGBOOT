package com.mc.mvc.module.epl;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.mvc.module.epl.dto.EplRankDto;

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
public class EplRank {
	
	@Id
	@GeneratedValue
	private Long eplIdx;
	private String team;
	private Integer matchCnt;
	private Integer win;
	private Integer draw;
	private Integer lose;
	private Integer goalsFor;
	private Integer goalsAganist;
	private Integer goalsDiffrence;
	private Integer point;
	@Builder.Default
	private LocalDateTime regDate = LocalDateTime.now();
	
	public static EplRank createEplRank(EplRankDto dto) {
		return EplRank.builder()
				.goalsAganist(dto.getGoalsAganist())
				.goalsFor(dto.getGoalsFor())
				.goalsDiffrence(dto.getGoalsDiffrence())
				.team(dto.getTeam())
				.matchCnt(dto.getMatchCnt())
				.win(dto.getWin())
				.lose(dto.getLose())
				.point(dto.getPoint())
				.draw(dto.getDraw())
				.build();
	}
	
	
	
	
}











