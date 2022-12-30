package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.AuthorService;

@GraphQlTest
class AuthorControllerTest {

	@MockBean
	private AuthorService authorService;

	@MockBean
	private BookRepo bookRepo;

	@MockBean
	private HttpGraphQlClient httpClient;

	@Autowired
	private GraphQlTester tester;

	@Test
	void testGetAllAuthors() {

		// language=GraphQL
		String document = """
				query {
					getAuthors {
				                id
				                name
				       }}
				""";
		when(bookRepo.save(ArgumentMatchers.any())).thenReturn(new Book());
		Iterable<Author> authorsList = List.of(new Author("Mr. X"));
		when(authorService.getAuthors()).thenReturn(authorsList);
		tester.document(document).execute().path("getAuthors").entityList(Author.class).hasSize(1);
	}

	@Test
	void testGetAllAuthorsWithBooks() {

		// language=GraphQL
		String document = """
				query {
					getAuthors {
				                id
				                name
				                books{name,price}
				       }}
				""";
		Author mockAuthor=new Author("Mr. X");
		Iterable<Author> authorsList = List.of(mockAuthor);
		Map<Author,List<Book>>booksResolver = new HashMap<>();
		booksResolver.put(mockAuthor, List.of(new Book("Some Book", 0, 0, mockAuthor)));
		
		when(bookRepo.save(ArgumentMatchers.any())).thenReturn(new Book());
		
		when(authorService.getAuthors()).thenReturn(authorsList);
		
		when(authorService.getBooks(ArgumentMatchers.any())).thenReturn(booksResolver);
		tester.document(document).execute().path("getAuthors").entityList(Author.class)
		.satisfies(authors->{
			assertEquals(1, authors.size());
			assertTrue(!authors.get(0).getBooks().isEmpty());
			assertEquals("Some Book",authors.get(0).getBooks().get(0).getName());
		});
	}
	
	@Test
	void testGetAuthorById() {

		// language=GraphQL
		String document = """
				query findOne($id: ID!) {
					getAuthorById(id: $id) {
				                id
				                name
				       }}
				""";
		when(bookRepo.save(ArgumentMatchers.any())).thenReturn(new Book());
		when(authorService.getAuthorById(ArgumentMatchers.eq(Long.valueOf(2)))).thenReturn(new Author("Mr.X"));
		tester.document(document).variable("id", 2).execute().path("getAuthorById").entity(Author.class)
		.satisfies(author->{
			assertEquals("Mr.X", author.getName());
		});
	}

}