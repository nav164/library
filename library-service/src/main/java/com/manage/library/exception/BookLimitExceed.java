package com.manage.library.exception;

public class BookLimitExceed  extends RuntimeException{

	public BookLimitExceed(String exception) {
		super(exception);
	}
}
