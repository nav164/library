package com.manage.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.library.service.BookService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@Api(value="Library Management API")
@RequestMapping("/")
@RequiredArgsConstructor
public class ManageLibrary {

	private final BookService bookService;


}
