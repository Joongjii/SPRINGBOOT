package com.mc.bookmanager.book;

import java.util.List;

import javax.persistence.EntityManager;

import com.mc.bookmanager.book.dto.BookDto;
import com.mc.bookmanager.jpa.EntityTemplate;
import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.member.MemberRepository;
import com.mc.bookmanager.member.dto.MemberDto;

public class BookService {
	
	private BookRepository bookRepository = new BookRepository();
	
	public List<BookDto> findAllBook() {
		List<Book> books = null;
		EntityManager em = EntityTemplate.getEntityManager();
		
		try {
			books = bookRepository.findAllBook(em);
		} finally {
			em.close();
		}
		
		return BookDto.toDoList(books);
	}

}
