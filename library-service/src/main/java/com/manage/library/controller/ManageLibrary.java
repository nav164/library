package com.manage.library.controller;

import com.manage.library.utility.LibraryConstant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Book;
import io.swagger.model.Borrow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.manage.library.service.BookService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@Api(value="Library Management API")
@RequestMapping("/")
@RequiredArgsConstructor
public class ManageLibrary {

	private final BookService bookService;

	@ApiOperation(value = "Get all books", nickname = "getBooks", notes = "Get all books", tags={ "Get all books from library", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK") })
	@GetMapping(value="/", produces = { "application/json", "application/stream+json", "text/event-stream" })
	public Flux<Book> getBooks() {
		return this.bookService.getBooks()
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, LibraryConstant.book_not_found)));
	}

	@ApiOperation(value = "Borrow books", nickname = "borrowBook", notes = "Borrow books", tags={ "Borrow books from library", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK") })
	@PutMapping(value="/", produces = { "application/json", "application/stream+json", "text/event-stream" })
	public Flux<Book> borrowBook(@RequestBody Borrow borrow) {
		return this.bookService.borrowBook(borrow);
	}

	@ApiOperation(value = "Return books", nickname = "returnBook", notes = "Return books", tags={ "Return books from library", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK") })
	@PutMapping(value="/return", produces = { "application/json", "application/stream+json", "text/event-stream" })
	public Flux<Book> returnBook(@RequestBody Borrow borrow) {
		return this.bookService.returnBook(borrow);
	}

}
