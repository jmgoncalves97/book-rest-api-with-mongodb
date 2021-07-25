package com.example.books.exception;

import java.lang.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String menssage, int id){
        super(String.format(menssage, id));
    }
}
