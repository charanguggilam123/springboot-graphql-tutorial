package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Book {

	@Id
	@SequenceGenerator(sequenceName = "book_id_seq", initialValue = 1, name = "book_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
	private long id;

	private String name;

	private int pages;

	private int price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author author;

	public Book(String name, int pages, int price) {
		super();
		this.name = name;
		this.pages = pages;
		this.price = price;
	}
	
	public Book(String name, int pages, int price, Author author) {
		super();
		this.name = name;
		this.pages = pages;
		this.price = price;
		this.author=author;
	}
	
	public Book() {
		
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPages() {
		return pages;
	}

	public int getPrice() {
		return price;
	}

	public Author getAuthor() {
		return author;
	}
	
	
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", pages=" + pages + ", price=" + price + "]";
	}
	
	

	
}
