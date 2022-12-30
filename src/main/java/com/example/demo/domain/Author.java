package com.example.demo.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Author {
	
	@Id
	@SequenceGenerator(initialValue = 1,name = "author_id_gen",sequenceName = "author_id_gen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_id_gen" )
	private long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
	@Fetch(FetchMode.SUBSELECT)
	private List<Book> books;

	public Author(String name) {
		super();
		this.name = name;
	}
	
	public Author() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return id == other.id && Objects.equals(name, other.name);
//		return Objects.equals(books, other.books) && id == other.id && Objects.equals(name, other.name);
	}
	
	

}
