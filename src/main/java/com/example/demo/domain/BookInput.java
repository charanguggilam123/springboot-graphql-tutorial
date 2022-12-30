package com.example.demo.domain;

public class BookInput {

	private String name;

	private int pages;

	private int price;

	private Long authorId;

	public BookInput(String name, int pages, int price, Long authorId) {
		super();
		this.name = name;
		this.pages = pages;
		this.price = price;
		this.authorId = authorId;
	}

	public BookInput() {

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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [ name=" + name + ", pages=" + pages + ", price=" + price + "]";
	}

}
