package com.mc.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mc.bookmanager.book.Book;
import com.mc.bookmanager.jpa.EntityTemplate;
import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.rent.dto.RentDto;

public class RentService {
	private RentRepository rentRepository = new RentRepository();
	
	public RentDto findRentByIdx(long rmIdx) {
		EntityManager em = EntityTemplate.getEntityManager();
		RentDto dto = null;
		try {
			Rent rent = em.find(Rent.class, rmIdx);
			dto = new RentDto(rent);
		} finally {
			em.close();
		}
		return dto;
	}

	public boolean createRent(String userId, List<Long> bkIdxs) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			//대출명
			//첫번째 대출도서명 외 n권
			
			List<Book> books = new ArrayList<Book>();		
			
			for (Long bkIdx : bkIdxs) {
				books.add(em.find(Book.class, bkIdx));
			}
			String title = books.size() > 1 ? books.get(0).getTitle() + "외"+ (books.size()-1)+"권"
											:books.get(0).getTitle();
			
			Member member = em.find(Member.class, userId);
			
			Rent rent =Rent.createRent(title, member,books.size());
			List<RentBook> rentBooks = new ArrayList<RentBook>();
			
			for(Book book:books) {
				rentBooks.add(RentBook.createRentBook(rent, book, "대출")); //rent 빼도
			}
			
			rent.addRentBooks(rentBooks);
			
			em.persist(rent);
			tx.commit();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}

	
	public boolean returnRentBook(long rbIdx) {
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.returnRentBook("반납", LocalDateTime.now());
			
			//모든 대출도서가 반납처리되면 대출테이블에서 대출상태를 반납으로 함께 변경		
			
			//rentBook테이블에서 해당 렌트북의 알엠아이디엑스로 대출도서를 모두 조회
			//조회한 모든대출도서의 상태값을 확인
			//상태값에 따라 렌트 테이블에 로우를 수정 -- 아래 로직이 아니다 
			
			if(rentBook.getRent().getRentBooks().stream().allMatch(e -> e.getState().equals("반납"))) {
				rentBook.getRent().returnComplete();
			}
			
			tx.commit();
			return true;
	}catch(Exception e){
		e.printStackTrace();
		tx.rollback();
	} finally {
		em.close();
	}
		
		return false;
	}

	public List<RentDto> findRentByUserId(String userId) {
		EntityManager em = EntityTemplate.getEntityManager();
		List<RentDto> rentDtos = null;
		
		try {
			List<Rent> rents = rentRepository.findRentByUserId(em, userId);
			rentDtos = RentDto.toDtoList(rents);
			
		}finally {
			em.close();
		}
		
		return rentDtos;
	}
	
	
	
	
	

}
