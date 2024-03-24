package ru.nonoka.bookstopten.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.nonoka.bookstopten.validation.annotations.ValidYear;

import java.util.Optional;

public class RequestYearValidation implements ConstraintValidator<ValidYear, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            int length = 4;
            boolean result = Optional.ofNullable(value)
                    .filter(y -> y.length() == length)
                    .map(Integer::parseInt)
                    .isPresent();
            if (!result) {
                throw new IllegalArgumentException("Invalid year: %s".formatted(value));
            }
        }
        return true;
    }
}
