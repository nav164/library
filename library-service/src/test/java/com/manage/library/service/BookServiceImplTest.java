package com.manage.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manage.library.api.LibraryApi;
import com.manage.library.exception.BookLimitExceed;
import com.manage.library.exception.CopyAlreadyBorrowedException;
import com.manage.library.utility.LibraryUtility;

import io.swagger.model.Book;
import io.swagger.model.Borrow;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = LibraryApi.class)
public class BookServiceImplTest {
	
	
	@Autowired
	private BookService bookService;
	
	@Test
	@DisplayName("should fetch all the books")
	public void shouldGetAllBooks() {
		Flux<Book> bookList = this.bookService.getBooks();
		
		Predicate<Book> match = book -> bookList.any(b -> b.equals(book)).block();
		
		StepVerifier.create(bookList)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextCount(LibraryUtility.books().size() - 3)
		.verifyComplete();
	}
	
	@Test
	@DisplayName("should borrow the book and return list of all the books")
	public void shouldBorrowTheBook() {
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		List<Book> bookLst = new ArrayList<Book>();
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		Flux<Book> bookList = this.bookService.borrowBook(borrow);
		
		Predicate<Book> match = book -> bookList.any(b -> b.equals(book)).block();
		
		StepVerifier.create(bookList)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextCount(LibraryUtility.books().size() - 3)
		.verifyComplete();
	}
	
	@Test
	@DisplayName("should throw an exception CopyAlreadyBorrowedException when user try to borrow the book he already have")
	public void shouldNotAllowBorrowingSameBook() {
		List<Book> bookLst = new ArrayList<Book>();
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		Borrow borrow = new Borrow();
		borrow.setUserId(2);
		borrow.setBook(bookLst);
		
		this.bookService.borrowBook(borrow).subscribe();
		
		try {
			this.bookService.borrowBook(borrow).subscribe();
		} catch(CopyAlreadyBorrowedException e) {
			Assertions.assertEquals("Copy of book already borrowed", e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("should throw an exception BookLimitExceed when user try to borrow the book he already have")
	public void shouldNotAllowBorrowMoreThenTwoBookWhileCallingBorrowMethod() {
		List<Book> bookLst = new ArrayList<Book>();
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		bk = new Book();
		bk.setIsbn("isbn2");
		bk.setName("name2");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		bk = new Book();
		bk.setIsbn("isbn3");
		bk.setName("name3");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		Borrow borrow = new Borrow();
		borrow.setUserId(3);
		borrow.setBook(bookLst);
		
		try {
			this.bookService.borrowBook(borrow).subscribe();
		} catch(BookLimitExceed e) {
			Assertions.assertEquals("Limit exceeded to borrow the book", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("should return the book and render list of all the books")
	public void shouldReturnTheBook() {
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		List<Book> bookLst = new ArrayList<Book>();
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		Flux<Book> bookList = this.bookService.returnBook(borrow);
		
		Predicate<Book> match = book -> bookList.any(b -> b.equals(book)).block();
		
		StepVerifier.create(bookList)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextMatches(match)
		.expectNextCount(LibraryUtility.books().size() - 3)
		.verifyComplete();
	}
	
	@Test
	@DisplayName("should return true if try to borrow one book")
	public void shouldAllowBorrow() {
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		List<Book> bookLst = new ArrayList<Book>();
		bookLst.add(bk);
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		Assertions.assertTrue(bookService.canBorrow(borrow));
	}
	
	@Test
	@DisplayName("should return false when try to borrow three book")
	public void shouldNotAllowBorrowMoreThenTwoBook() {
		List<Book> bookLst = new ArrayList<Book>();
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		bk = new Book();
		bk.setIsbn("isbn2");
		bk.setName("name2");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		bk = new Book();
		bk.setIsbn("isbn3");
		bk.setName("name3");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		bookLst.add(bk);
		
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		Assertions.assertFalse(bookService.canBorrow(borrow));
	}
	
	@Test
	@DisplayName("should return Book object if it is already borrowed")
	public void shouldReturnNullIfCopyOfBookNeverBorrowed() {
		Book bk = new Book();
		bk.setIsbn("isbn1");
		bk.setName("name1");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		List<Book> bookLst = new ArrayList<Book>();
		bookLst.add(bk);
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		Book book = this.bookService.isBorrowed(borrow);
		Assertions.assertNull(book);
	}
	
	@Test
	@DisplayName("should return Book object if it is already borrowed")
	public void shouldReturnBookObjectIfCopyOfBookAlredyBorrowed() {
		Book bk = new Book();
		bk.setIsbn("isbn5");
		bk.setName("name5");
		bk.setAuthor("Naveen");
		bk.setIsAvailable(true);
		bk.setPrice(200);
		bk.setUser(null);
		List<Book> bookLst = new ArrayList<Book>();
		bookLst.add(bk);
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookLst);
		
		this.bookService.borrowBook(borrow);
		Book book = this.bookService.isBorrowed(borrow);
		Assertions.assertEquals(bk, book);
	}
}
