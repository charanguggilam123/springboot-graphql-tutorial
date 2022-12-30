package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Author;
import com.example.demo.domain.AuthorInput;
import com.example.demo.domain.Book;
import com.example.demo.domain.BookInput;
import com.example.demo.exception.DBException;
import com.example.demo.repo.AuthorRepo;
import com.example.demo.repo.BookRepo;

@Service
public class AuthorService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private AuthorRepo authorRepo;

	public Iterable<Author> getAuthors() {
		return authorRepo.findAll();

	}

	public Map<Author, List<Book>> getBooks(List<Author> authors) {
		System.out.println("***");
		List<Long> authorIds = authors.stream().map(author -> author.getId()).collect(Collectors.toList());
		List<Book> resp = bookRepo.getByAuthorIds(authorIds);
		resp.stream().forEach(System.out::println);
		Map<Author, List<Book>> newResp = resp.stream().collect(Collectors.groupingBy(book -> {
			return book.getAuthor();
		}));
		System.out.println(newResp);
		return newResp;
//		return Map.of(authors.get(0),List.of(new Book("Some!!", 12, 123, authors.get(0))));
//		return Map.of(resp.get(0).getAuthor(),List.of(new Book("Some!!", 12, 123, authors.get(0))));

	}

	public Author getAuthorById(@Argument Long id) {
		Optional<Author> author = authorRepo.findById(id);
		if (!author.isPresent())
			throw new DBException("Author is not found in db");
		return author.get();

	}

	public Author addAuthor(AuthorInput authorInput) {
		// TODO Auto-generated method stub
		Author author = new Author();
		author.setName(authorInput.getName());
		if (authorInput.getBooks() != null && !authorInput.getBooks().isEmpty())
			author.setBooks(prepareBooks(null, authorInput.getBooks()));
		return authorRepo.save(author);
	}

	public Author updateAuthor(Long id, AuthorInput authorInput) {
		Optional<Author> author = authorRepo.findById(id);
		if (!author.isPresent())
			throw new DBException("The author you're trying to update is not present");
		Author authorToUpdate = author.get();
		if (authorInput.getName() != null)
			authorToUpdate.setName(authorInput.getName());
		if (authorInput.getBooks() != null && !authorInput.getBooks().isEmpty()) {
			authorToUpdate.setBooks(prepareBooks(author.get(), authorInput.getBooks()));
		}
		return authorRepo.save(authorToUpdate);
	}

	private List<Book> prepareBooks(Author author, List<BookInput> books) {

		return books.stream().map(bookInput -> {

			Book book = new Book(bookInput.getName(), bookInput.getPages(), bookInput.getPrice());
			if (author != null)
				book.setAuthor(author);

			return book;
		}).toList();
	}

	public Author deleteAuthor(Long id) {
		Optional<Author> author = authorRepo.findById(id);
		if (!author.isPresent())
			throw new DBException("The author you're trying to update is not present");
		authorRepo.deleteById(id);
		return author.get();
	}
}
