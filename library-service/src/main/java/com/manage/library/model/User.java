package com.manage.library.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	private int userId;
	private String name;
	private int age;
	private boolean isMember;
}
