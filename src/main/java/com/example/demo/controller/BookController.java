package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.domain.Book;
import com.example.demo.repo.BookRepo;

@Controller
public class BookController {

	@Autowired
	private BookRepo bookRepo;

	@QueryMapping(name = "booksByAuthor")
	List<Book> miscBook() {

		System.out.println("fetching all start");
		bookRepo.getByAuthorIds(List.of(Long.valueOf(1), Long.valueOf(2), Long.valueOf(3))).stream()
				.forEach(System.out::println);

		System.out.println("fetching one");
		return bookRepo.getByAuthorId(2);

	}

}
