package com.mc.bookmanager.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.bookmanager.book.Book;
import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.member.dto.MemberDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BookDto {
	private Long bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	private Integer bookAmt;
	private LocalDateTime regDate;
	private Integer rentCnt;
	
	public BookDto(Book entity) {
		this.bkIdx = entity.getBkIdx();
		this.isbn = entity.getIsbn();
		this.category =entity.getCategory();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.info =entity.getInfo();
		this.bookAmt=entity.getBookAmt();
		this.regDate=entity.getRegDate();
		this.rentCnt=entity.getRentCnt();
	}
	
	public static List<BookDto> toDtoList(List<Book> entityList){
		return entityList.stream().map(e-> new BookDto(e)).collect(Collectors.toList());
	}
	
	
	
}
