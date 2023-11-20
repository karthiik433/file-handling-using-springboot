package com.example.filehandling.exceptionHandler;

import com.example.filehandling.exception.CustomException;
import com.example.filehandling.exception.ExceptionResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseObject handleControllerException(Exception exception){
        log.info("Handling the controller exception");

        return new ExceptionResponseObject(HttpStatus.BAD_REQUEST,exception.getMessage(),"Please send valid request");
    }
}
