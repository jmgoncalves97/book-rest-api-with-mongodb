package com.example.books.repository;

import com.example.books.model.BookDocument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookDocument, Integer>{}
