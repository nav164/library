package com.manage.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.manage.library.exception.BookLimitExceed;
import com.manage.library.exception.BookAlreadyBorrowedException;
import com.manage.library.mapper.BookMapper;
import com.manage.library.model.BookEntity;
import com.manage.library.model.User;
import com.manage.library.utility.LibraryConstant;
import com.manage.library.utility.LibraryUtility;

import io.swagger.model.Book;
import io.swagger.model.Borrow;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{
	
	private static final List<BookEntity> bookList = LibraryUtility.books();
	
	private static final Map<Integer, User> userMap = LibraryUtility.getUserDetails();
	
	/**
	 * This method will fetch all the books detail
	 * @author Naveen
	 * @return stream of Book object
	 */
	public Flux<Book> getBooks() {
		return Flux.fromIterable(BookMapper.mapBook(bookList));
	}

	/**
	 * This method will update the borrow details in the list
	 * @author Naveen
	 * @return Stream of updated list
	 */
	public Flux<Book> borrowBook(Borrow borrow) {
		if(!this.canBorrow(borrow))
			throw new BookLimitExceed(LibraryConstant.limit_exceeds);
		if(null != this.isBorrowed(borrow))
			throw new BookAlreadyBorrowedException(LibraryConstant.already_borrowed);
		borrow.getBook().forEach(book -> {
			for(BookEntity bookEntity : bookList) {
				if(bookEntity.getIsbn().equalsIgnoreCase(book.getIsbn()) && bookEntity.isAvailable()) {
					bookEntity.setAvailable(false);
					bookEntity.setUser(userMap.get(borrow.getUserId()));
					break;
				}
			}
		});
		return Flux.fromIterable(BookMapper.mapBook(bookList));
	}
	
	/**
	 * This method will update the return details in the list
	 * @author Naveen
	 * @return Stream of updated list
	 */
	public Flux<Book> returnBook(Borrow borrow) {
		borrow.getBook().forEach(book -> {
			for(BookEntity bookEntity : bookList) {
				if(bookEntity.getIsbn().equalsIgnoreCase(book.getIsbn()) 
						&& !bookEntity.isAvailable() 
						&& bookEntity.getUser().getUserId() == borrow.getUserId()) {
					bookEntity.setAvailable(true);
					bookEntity.setUser(null);
					break;
				}
			}
		});
		return Flux.fromIterable(BookMapper.mapBook(bookList));
	}
	
	/**
	 * This method will give the details if book or its copy already borrowed
	 * @author Naveen
	 * @param borrow Borrow object
	 * @return Mono of Book object
	 */
	public Book isBorrowed(Borrow borrow) {
		for(Book requestedBook: borrow.getBook()) {
			long count = bookList.stream()
					.filter(b -> (b.getIsbn().equalsIgnoreCase(requestedBook.getIsbn()) && null != b.getUser() && b.getUser().getUserId() == borrow.getUserId()))
					.count();
			if(count > 0)
				return requestedBook;
		}
		return null;
	}
	
	/**
	 * This method will indicate if user have scope the borrow the book. 
	 * i.e. if user already borrowed 2 books then method will return false
	 * @author Naveen
	 * @param borrow Borrow object
	 * @return boolean
	 */
	public boolean canBorrow(Borrow borrow) {
		long bookCount;
		bookCount = bookList.stream()
				.filter(book -> (null != book.getUser() && book.getUser().getUserId() == borrow.getUserId()))
				.count();
		return borrow.getBook().size() <= 2 && bookCount != 2;
	}
}   
