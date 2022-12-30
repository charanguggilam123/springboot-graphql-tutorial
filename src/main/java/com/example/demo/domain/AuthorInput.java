package com.example.demo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorInput {
	
	
	private String name;
	private List<BookInput> books;

	public AuthorInput(String name) {
		super();
		this.name = name;
	}
	
	public AuthorInput() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookInput> getBooks() {
		return books;
	}

	public void setBooks(List<BookInput> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "AuthorInput [name=" + name + ", books=" + books + "]";
	}
	
	
	
	

}
