package com.mc.bookmanager.book;

import java.util.List;
import com.mc.bookmanager.book.dto.BookDto;

public class BookController {
	private BookService bookService = new BookService();
	
	public List<BookDto> findAllBook() {
		return bookService.findAllBook();
	}

}
