package com.manage.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.manage.library.api.LibraryApi;
import com.manage.library.mapper.BookMapper;
import com.manage.library.model.BookEntity;
import com.manage.library.service.BookService;
import com.manage.library.utility.LibraryUtility;

import io.swagger.model.Book;
import io.swagger.model.Borrow;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = ManageLibrary.class)
@ContextConfiguration(classes = LibraryApi.class)
public class ManageLibraryTest {

	@MockBean
	BookService bookService;

	@Autowired
	private WebTestClient webClient;

	@Test
	@DisplayName("should return all the books")
	public void shouldListAllTheBooks() throws Exception {
		Flux<Book> bookFlux = Flux.fromIterable(BookMapper.mapBook(LibraryUtility.books()));

		Mockito.when(bookService.getBooks())
				.thenReturn(bookFlux);

		webClient.get().uri("/")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Book.class).hasSize(LibraryUtility.books().size())
				.isEqualTo(BookMapper.mapBook(LibraryUtility.books()));
	}

	@Test
	@DisplayName("should return http status 204 (No book found)")
	public void shouldReturnNoBook() throws Exception {
		Flux<Book> bookFlux = Flux.fromIterable(BookMapper.mapBook(new ArrayList<BookEntity>()));

		Mockito.when(bookService.getBooks())
				.thenReturn(bookFlux);

		webClient.get().uri("/")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	@DisplayName("should return the list of books with borrowed details")
	public void shouldBorrowTheBook() throws Exception {
		Flux<Book> bookFlux = Flux.fromIterable(BookMapper.mapBook(LibraryUtility.books()));

		Book book = new Book();
		book.setIsbn("isbn1");
		book.setName("name1");
		book.setAuthor("Naveen");
		book.setIsAvailable(true);
		book.setPrice(200);
		book.setUser(null);
		List<Book> bookList = new ArrayList<Book>();
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookList);

		Mockito.when(bookService.borrowBook(borrow))
				.thenReturn(bookFlux);

		webClient.put().uri("/")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(borrow))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Book.class).hasSize(LibraryUtility.books().size())
				.isEqualTo(BookMapper.mapBook(LibraryUtility.books()));
	}

	@Test
	@DisplayName("should return the book to the library")
	public void shouldReturnTheBook() throws Exception {
		Flux<Book> bookFlux = Flux.fromIterable(BookMapper.mapBook(LibraryUtility.books()));

		Book book = new Book();
		book.setIsbn("isbn1");
		book.setName("name1");
		book.setAuthor("Naveen");
		book.setIsAvailable(true);
		book.setPrice(200);
		book.setUser(null);
		List<Book> bookList = new ArrayList<Book>();
		Borrow borrow = new Borrow();
		borrow.setUserId(1);
		borrow.setBook(bookList);

		Mockito.when(bookService.returnBook(borrow))
				.thenReturn(bookFlux);

		webClient.put().uri("/return")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(borrow))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Book.class).hasSize(LibraryUtility.books().size())
				.isEqualTo(BookMapper.mapBook(LibraryUtility.books()));
	}
}
