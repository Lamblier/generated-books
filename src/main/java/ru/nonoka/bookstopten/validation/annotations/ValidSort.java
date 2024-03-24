package ru.nonoka.bookstopten.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.nonoka.bookstopten.validation.RequestSortValidation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = RequestSortValidation.class)
public @interface ValidSort {

    String message() default "{ValidSort.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
