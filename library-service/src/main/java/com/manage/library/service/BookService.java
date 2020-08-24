package com.manage.library.service;

import io.swagger.model.Book;
import io.swagger.model.Borrow;
import reactor.core.publisher.Flux;

import java.util.List;

public interface BookService {
	Flux<Book> getBooks();
	List<Book> borrowBook(Borrow borrow);
	List<Book> returnBook(Borrow borrow);
}
