package com.example.books.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.books.builder.BookBuilder;
import com.example.books.exception.ResourceNotFoundException;
import com.example.books.model.BookDocument;
import com.example.books.repository.BookRepository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    
    private static final Integer ID = 1;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void whenPutBookThenReturnBook() throws ResourceNotFoundException {

        BookDocument book = BookBuilder.bookBuilder();

        Mockito.when(bookRepository.findById(ID)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        MatcherAssert.assertThat(bookService.update(ID, book), Matchers.is(Matchers.equalTo(book)));
    }

    @Test
    public void whenPutBookThatIdDoesntExistThenThrowsException() {

        BookDocument book = BookBuilder.bookBuilder();

        when(bookRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookService.update(ID, book));
    }
}
