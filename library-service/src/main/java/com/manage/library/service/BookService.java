package com.manage.library.service;

import io.swagger.model.Book;
import io.swagger.model.Borrow;
import reactor.core.publisher.Flux;

public interface BookService {
	Flux<Book> getBooks();
	Flux<Book> borrowBook(Borrow borrow);
	Flux<Book> returnBook(Borrow borrow);
}
