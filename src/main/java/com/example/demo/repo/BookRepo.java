package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;

import com.example.demo.domain.Book;

@GraphQlRepository
public interface BookRepo extends JpaRepository<Book, Long>{
	
	@Query(value = "select * from book b where b.author_id=?1",nativeQuery = true)
	List<Book> getByAuthorId(long authorId);
	
	@Query(value = "select * from book b where b.author_id In (:authorIds)",nativeQuery = true)
	List<Book> getByAuthorIds(@Param("authorIds") List<Long> authorId);
	

}
