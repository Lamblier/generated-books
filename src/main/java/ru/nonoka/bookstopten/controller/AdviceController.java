package ru.nonoka.bookstopten.controller;


import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nonoka.bookstopten.exception.ExceptionBody;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody GenerateJsonBook(ValidationException e) {
        return new ExceptionBody(e.getCause().getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleException(final Exception e) {
        e.printStackTrace();
        return new ExceptionBody("Internal error.");
    }
}
