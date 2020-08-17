package com.manage.library.mapper;

import java.util.ArrayList;
import java.util.List;

import com.manage.library.model.BookEntity;

import io.swagger.model.Book;

public class BookMapper {

	/**
	 * This method will map the model object of book to response object of book
	 * @author Naveen
	 * @param bookEntity
	 * @return List of books
	 */
	public static List<Book> mapBook(List<BookEntity> bookEntity) {
		List<Book> bookList = new ArrayList<Book>();
		bookEntity.forEach(b -> {
			Book book = new Book();
			book.setIsbn(b.getIsbn());
			book.setName(b.getName());
			book.setAuthor(b.getAuthor());
			book.setPrice(b.getPrice());
			book.setUser(b.getUser());
			book.setIsAvailable(b.isAvailable());
			bookList.add(book);
		});
		return bookList;
	}
}
