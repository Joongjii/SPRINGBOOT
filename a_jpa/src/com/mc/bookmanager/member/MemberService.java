package com.mc.bookmanager.member;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mc.bookmanager.jpa.EntityTemplate;
import com.mc.bookmanager.member.dto.MemberDto;

public class MemberService {
	
	private MemberRepository memberRepository = new MemberRepository();
	
	public MemberDto findMemberById(String userId) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		Member member = null;
		
		try {
			member = em.find(Member.class, userId);
		} finally {
			//EntityManager 종료
			//EntityManager를 종료하면 Entity들은 더이상 EntityManager의 관리대상이 아니게된다.
			// close : 엔티티매니저 종료
			// clear : 엔티티매니저 초기화 엔티티매니저가 관리하고있던 모든 엔티티들을 준영속 상태로 변경
			// detach : 특정 엔티티를 준영속 상태로 변경할때 사용
			em.close();
		}
		
		return new MemberDto(member);
	}
	public List<MemberDto> findAllMember(){
		
		List<Member> members = null;
		EntityManager em = EntityTemplate.getEntityManager();
		
		try {
			members = memberRepository.findAllMember(em);
		} finally {
			em.close();
		}
		
		return MemberDto.toDoList(members);
	}
	
	public boolean createMember(MemberDto dto) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//트랜잭션 시작
		tx.begin();
		
		try {
			em.persist(Member.createMember(dto));
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}
	public boolean updateMember(MemberDto memberDto) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		System.out.println(memberDto);
		
		try {
			
			//영속 상태 entity
			Member entity = em.find(Member.class, memberDto.getUserId());
			
			//엔티티매니저가 관리하는 엔티티의 속성이 변경되었음으로
			//엔티티매니저는 트랜잭션이 커밋되는 시점에 변경된 내용을 영속화 -> dirty check
			entity.updateMember(memberDto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		
		return false;
	}
	public List<MemberDto> findMemberByRegDate(LocalDateTime begin, LocalDateTime end) {


		EntityManager em = EntityTemplate.getEntityManager();
		List<Member> members = null;
		
		try {
			 members = memberRepository.findMemberByRegDate(em, begin, end);
		} finally {
			em.close();
		}
		return MemberDto.toDoList(members);
	}
	
	public boolean removeMemberByUserId(String userId) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = em.find(Member.class, userId);
			em.remove(member);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}
	
	
	
	
	
	
	
}
