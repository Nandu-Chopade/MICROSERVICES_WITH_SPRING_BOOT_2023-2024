package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseMessage> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex ){


        ResponseMessage resp = ResponseMessage.builder()
                .message(ex.getMessage())
                .status("404")
                .date(new Date())
                .build();
        return new ResponseEntity<ResponseMessage>(resp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseMessage> httpRequestMethodNotSupportedException(NoSuchElementException ex ){


        ResponseMessage resp = ResponseMessage.builder()
                .message(ex.getMessage())
                .status("404")
                .date(new Date())
                .build();
        return new ResponseEntity<ResponseMessage>(resp,HttpStatus.NOT_FOUND);
    }
}
