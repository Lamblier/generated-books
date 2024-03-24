package ru.nonoka.bookstopten.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import ru.nonoka.bookstopten.model.Book;
import ru.nonoka.bookstopten.validation.annotations.ValidColumn;

import java.util.Arrays;

public class RequestColumnValidation implements ConstraintValidator<ValidColumn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = Arrays.stream(Book.class.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(value));

        if (!result) {
            throw new ValidationException("Invalid column: %s".formatted(value));
        }
        return true;
    }
}
