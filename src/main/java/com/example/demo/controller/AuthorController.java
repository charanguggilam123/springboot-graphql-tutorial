package com.example.demo.controller;

import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Author;
import com.example.demo.domain.AuthorInput;
import com.example.demo.domain.Book;
import com.example.demo.service.AuthorService;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private HttpGraphQlClient httpClient;
	
	private static final Logger log =LoggerFactory.getLogger(AuthorController.class);

	@GetMapping("/getAuthors")
	public List<Author> getAuthorsHttp(HttpServletRequest request) {
		String document = "query sb{\r\n" + "  getAuthors{\r\n" + "    id\r\n" + "    name\r\n" + "    books{\r\n"
				+ "      name\r\n" + "      pages\r\n" + "      price\r\n" + "    }\r\n" + "  }  \r\n" + "}";
		return httpClient.document(document).retrieve("getAuthors").toEntityList(Author.class).block();
	}

	@GetMapping("/get-author-by-id/{id}")
	public Author getAuthorsByIdHttp(HttpServletRequest request,@PathVariable Long id) {
//		String base64Encoded=request.getHeader("authorization").split(" ")[1];
//		String str = new String(Base64.getDecoder().decode(base64Encoded));
		String document = """
				query authorDetails($id:ID!){
					getAuthorById(id: $id){
						name
						books{
							name,pages,price
						}
					}
				}

				""";
		return httpClient.document(document).variable("id", id).retrieve("getAuthorById").toEntity(Author.class).block();
	}

	@QueryMapping
	@PreAuthorize("hasRole('ADMIN')")
	Iterable<Author> getAuthors() {
		return authorService.getAuthors();

	}

	/*
	 * @SchemaMapping(typeName = "Author") // but this also does 1+n calls to fetch
	 * data public List<Book> books(Author author){ log.info("***");
	 * return bookRepo.getByAuthorId(author.getId()); }
	 */

	@BatchMapping
//	  This is initial solution for batch loading but doesn't work in reactive environment
	Map<Author, List<Book>> booksOld(List<Author> authors) {
		log.info("in books method");
		Map<Author, List<Book>> newResp = authors.stream().collect(Collectors.toMap(author -> author, author -> {
			return author.getBooks();
		}));
		log.info("response received: {}",newResp);
		return newResp;

	}

	@BatchMapping
	Map<Author, List<Book>> books(List<Author> authors) {
		log.info("resolving books using batch loading");
		return authorService.getBooks(authors);

	}

	@QueryMapping
	@Secured({ "ROLE_USER"})
	Author getAuthorById(@Argument Long id) {

		return authorService.getAuthorById(id);

	}

	@MutationMapping
	@Secured({"ROLE_ADMIN" })
	Author addAuthor(@Argument AuthorInput author) {

		return authorService.addAuthor(author);

	}

	@MutationMapping
	Author updateAuthor(@Argument Long id, @Argument AuthorInput author) {
		log.info("author id: {}" , id);
		return authorService.updateAuthor(id, author);

	}

	@MutationMapping
	Author deleteAuthorById(@Argument Long id) {

		return authorService.deleteAuthor(id);

	}

}
