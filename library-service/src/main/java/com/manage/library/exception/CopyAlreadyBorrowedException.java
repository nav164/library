package com.manage.library.exception;

public class CopyAlreadyBorrowedException extends RuntimeException {
	public CopyAlreadyBorrowedException(String exception) {
		super(exception);
	}
}
