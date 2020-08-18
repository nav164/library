package com.manage.library.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.library.model.BookEntity;
import com.manage.library.model.User;


public class LibraryUtility {

	public static List<BookEntity> books() {
		List<BookEntity> bookList = new ArrayList<>();

		BookEntity book = new BookEntity();
		book.setIsbn("isbn1");
		book.setName("book1");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn2");
		book.setName("book2");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn3");
		book.setName("book3");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn1");
		book.setName("book1");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn4");
		book.setName("book4");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn5");
		book.setName("book5");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn6");
		book.setName("book6");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn5");
		book.setName("book5");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn7");
		book.setName("book7");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		book = new BookEntity();
		book.setIsbn("isbn8");
		book.setName("book8");
		book.setAuthor("Naveen");
		book.setPrice(500);
		book.setAvailable(true);
		book.setUser(null);
		bookList.add(book);

		return bookList;
	}

	public static Map<Integer, User> getUserDetails() {
		Map<Integer, User> userMap = new HashMap<>();

		User user = new User();
		user.setUserId(1);
		user.setName("Naveen");
		user.setAge(29);
		user.setMember(true);
		userMap.put(user.getUserId(), user);

		user = new User();
		user.setUserId(2);
		user.setName("Praveen");
		user.setAge(27);
		user.setMember(true);
		userMap.put(user.getUserId(), user);

		user = new User();
		user.setUserId(3);
		user.setName("John");
		user.setAge(31);
		user.setMember(true);
		userMap.put(user.getUserId(), user);
		return userMap;
	}


}
