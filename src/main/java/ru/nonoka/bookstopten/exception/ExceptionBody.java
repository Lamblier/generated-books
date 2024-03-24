package ru.nonoka.bookstopten.exception;

import lombok.Data;

@Data
public class ExceptionBody {
    private String message;

    public ExceptionBody(final String message) {
        this.message = message;
    }
}
