package com.example.restapi.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotExceptionResponse extends RuntimeException{
    public PostNotExceptionResponse(String message){
        super(message);
    }
}
