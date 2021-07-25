package com.example.books.service;

import java.util.List;

import com.example.books.exception.ResourceNotFoundException;
import com.example.books.model.BookDocument;
import com.example.books.repository.BookRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public BookDocument create(BookDocument bookDocument) {
        return bookRepository.save(bookDocument);
    }

    public List<BookDocument> getAll() {
        return bookRepository.findAll();
    }

    public BookDocument getById(int id) throws ResourceNotFoundException {
        bookRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Livro com id %d não encontrado", id));
        return bookRepository.findById(id).get();
    }

    public BookDocument update(int id, BookDocument bd) throws ResourceNotFoundException {
        BookDocument bookDocument = bookRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Livro com id %d não encontrado", id));

        bookDocument.setBookName(bd.getBookName());
        bookDocument.setAuthor(bd.getAuthor());

        return bookRepository.save(bookDocument);
    }

    public void delete(int id) throws ResourceNotFoundException {
        BookDocument bookDocument = bookRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Livro com id %d não encontrado", id));

        bookRepository.delete(bookDocument);
    }
}
