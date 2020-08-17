package com.manage.library.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
	public BookAlreadyBorrowedException(String exception) {
		super(exception);
	}
}
