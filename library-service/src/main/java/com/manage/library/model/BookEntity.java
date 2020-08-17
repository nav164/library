package com.manage.library.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEntity {

	private int id;
	private String name;
	private String isbn;
	private String author;
	private int price;
	private boolean isAvailable;
	private User user;
}
