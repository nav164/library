package com.manage.library.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import com.manage.library.model.BookEntity;
import com.manage.library.utility.LibraryUtility;

import io.swagger.model.Book;


public class BookMapperTest {
	
	@Test
	@DisplayName("should map the BookEntity object to Book object")
	public void shouldMapBookEntityToBook() {
		List<BookEntity> bookEntity = LibraryUtility.books();
		List<Book> bookList = BookMapper.mapBook(bookEntity);
		
		Assertions.assertEquals(bookList.size(), bookEntity.size());
		Assertions.assertEquals(bookList.get(0).getIsbn(), bookEntity.get(0).getIsbn());
		Assertions.assertEquals(bookList.get(0).getName(), bookEntity.get(0).getName());
		Assertions.assertEquals(bookList.get(0).getAuthor(), bookEntity.get(0).getAuthor());
		Assertions.assertEquals(bookList.get(0).getPrice(), bookEntity.get(0).getPrice());
		Assertions.assertEquals(bookList.get(0).isIsAvailable(), bookEntity.get(0).isAvailable());
	}

}
