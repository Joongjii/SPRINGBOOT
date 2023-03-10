package com.mc.boot.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.boot.book.Book;
import com.mc.boot.book.dto.BookDto;

//jparepo
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryExtension{

	//도서명이나 작가로 도서를 검색할 경우
	List<Book> findByTitleOrAuthor(String title, String author);
	
	Integer countByCategory(String category);
	
	//"카테고리가 문학이고, bookAmt가 3 이상이면서 제목이 '디'로 시작하는 도서를 찾아보자"
	List<Book> findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith(String category, int bookAmt, String title);
	
}
