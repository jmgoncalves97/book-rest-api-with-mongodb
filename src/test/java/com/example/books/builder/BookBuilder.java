package com.example.books.builder;

import com.example.books.model.BookDocument;

public class BookBuilder {
    
    public static BookDocument bookBuilder() {
        return BookDocument.builder()
            .id(1)
            .bookName("O homem mais rico da Babilônia")
            .author("George Clason")
            .build();
    }
}
