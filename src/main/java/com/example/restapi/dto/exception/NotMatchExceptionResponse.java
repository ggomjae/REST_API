package com.example.restapi.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotMatchExceptionResponse extends RuntimeException {
    public NotMatchExceptionResponse(String message){
        super(message);
    }
}
