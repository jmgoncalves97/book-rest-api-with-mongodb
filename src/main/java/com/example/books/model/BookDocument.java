package com.example.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDocument {

    @Id
    private int id;

    private String bookName;

    private String author;
}
