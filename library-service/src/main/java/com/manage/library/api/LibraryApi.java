package com.manage.library.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages= {"com.manage.library"})
public class LibraryApi {
	public static void main(String... args) {
		new SpringApplicationBuilder().sources(LibraryApi.class)
		.run(args);
	}
}
