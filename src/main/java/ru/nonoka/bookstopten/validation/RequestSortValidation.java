package ru.nonoka.bookstopten.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.nonoka.bookstopten.validation.annotations.ValidSort;

import java.util.List;

public class RequestSortValidation implements ConstraintValidator<ValidSort, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!List.of("asc", "desc").contains(value.toLowerCase())) {
            throw new IllegalArgumentException("Invalid sort value: " + value);
        }
        return true;
    }
}
