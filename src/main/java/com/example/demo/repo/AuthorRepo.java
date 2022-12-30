package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import com.example.demo.domain.Author;

@GraphQlRepository
public interface AuthorRepo extends JpaRepository<Author, Long>{

}
