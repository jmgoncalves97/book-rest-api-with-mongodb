package com.example.books.controller;

import static com.example.books.builder.BookBuilder.bookBuilder;
import static com.example.books.mapper.BookMapper.objectToJson;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.books.exception.ResourceNotFoundException;
import com.example.books.model.BookDocument;
import com.example.books.resource.BookController;
import com.example.books.service.BookService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BookController.class)
public class WebMockTest {

    private static final int ID = 1;

    private static final String PATH_BOOK_ID_1 = ("/api/v1/books/" + ID);

    private static final String PATH_BOOKS = "/api/v1/books";

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;

    @Test
    public void whenGetBooksThenReturnOk() throws Exception{

        BookDocument book = bookBuilder();

        List<BookDocument> bookList = List.of(book);

        String bookJsonList = objectToJson(bookList);

        Mockito.when(bookService.getAll()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH_BOOKS))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(bookJsonList));
    }

    @Test
    public void whenGetBookByIdThenReturnOk() throws Exception{

        BookDocument book = bookBuilder();

        String bookJson = objectToJson(book);

        when(bookService.getById(ID)).thenReturn(book);

        mockMvc.perform(get(PATH_BOOK_ID_1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(content().json(bookJson));
    }

    @Test
    public void whenGetBookThatDoesntExistThenReturnBadRequest() throws Exception{

        when(bookService.getById(ID)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(PATH_BOOK_ID_1))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostBookThenReturnCreated() throws Exception{

        BookDocument book = bookBuilder();

        String bookJson = objectToJson(book);

        Mockito.any();

        when(bookService.create(book)).thenReturn(book);

        mockMvc.perform(post(PATH_BOOKS)
                .contentType(APPLICATION_JSON)
                .content(bookJson)
                .accept(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().json(bookJson));
    }

    @Test
    public void whenDeleteBookThenReturnNoContent() throws Exception {

        Mockito.doNothing().when(bookService).delete(ID);

        mockMvc.perform(delete(PATH_BOOK_ID_1))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteBookDoesntExistThenReturnNoContent() throws Exception {

        Mockito.doThrow(ResourceNotFoundException.class).when(bookService).delete(ID);

        mockMvc.perform(delete(PATH_BOOK_ID_1))
            .andExpect(status().isBadRequest());
    }
}
