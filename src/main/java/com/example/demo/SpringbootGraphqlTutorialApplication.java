package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repo.BookRepo;

@SpringBootApplication
public class SpringbootGraphqlTutorialApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGraphqlTutorialApplication.class, args);
	}

//	@Autowired
//	private AuthorRepo authorRepo;

	@Autowired
	private BookRepo bookRepo;

	@Override
	public void run(String... args) throws Exception {

		Author author1 = new Author("George R.R Martin");
		Author author2 = new Author("Stephen King");
//		Author author3 = new Author("J.k Rowling");

		Book book1 = new Book("House Of Dragon", 1200, 100, author1);
		Book book2 = new Book("Game Of Thrones", 567, 899, author1);
		Book book3 = new Book("CastleRock", 455, 499, author2);

		bookRepo.saveAll(Arrays.asList(book1, book2, book3));

	}

}
