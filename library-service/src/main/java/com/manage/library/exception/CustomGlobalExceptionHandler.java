package com.manage.library.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.model.ApiError;


@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	 @RequestMapping(produces = "application/json")
	 @ExceptionHandler(BookLimitExceed.class)
	 public ResponseEntity<?> handleException(BookLimitExceed ex) {
		 	ApiError apiError = new ApiError();
		 	apiError.setMessage(ex.getMessage());
		 	apiError.setStatus(HttpStatus.BAD_REQUEST.toString());
		 	apiError.setTimestamp(LocalDateTime.now().toString());
	        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	 }
	 
	 @RequestMapping(produces = "application/json")
	 @ExceptionHandler(BookAlreadyBorrowedException.class)
	 public ResponseEntity<?> handleException(BookAlreadyBorrowedException ex) {
		 	ApiError apiError = new ApiError();
		 	apiError.setMessage(ex.getMessage());
		 	apiError.setStatus(HttpStatus.BAD_REQUEST.toString());
		 	apiError.setTimestamp(LocalDateTime.now().toString());
	        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	 }
}
