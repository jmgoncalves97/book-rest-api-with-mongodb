package com.example.books.resource;

import java.util.List;

import com.example.books.model.BookDocument;
import com.example.books.service.BookService;

import com.example.books.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookDocument> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookDocument getById(@PathVariable int id) throws ResourceNotFoundException {
        return bookService.getById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookDocument create(@RequestBody BookDocument bookDocument) {
        return bookService.create(bookDocument);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BookDocument update(@PathVariable int id, @RequestBody BookDocument bookDocument)
            throws ResourceNotFoundException {
        return bookService.update(id, bookDocument);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws ResourceNotFoundException {
        bookService.delete(id);
    }
}
