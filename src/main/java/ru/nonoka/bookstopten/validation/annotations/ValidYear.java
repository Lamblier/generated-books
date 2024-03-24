package ru.nonoka.bookstopten.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.nonoka.bookstopten.validation.RequestYearValidation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = RequestYearValidation.class)
public @interface ValidYear {

    String message() default "{ValidYear.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
