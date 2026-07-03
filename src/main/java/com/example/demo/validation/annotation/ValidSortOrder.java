package com.example.demo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.example.demo.validation.validator.SortOrderValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SortOrderValidator.class)
public @interface ValidSortOrder {
    String message() default "Only asc, desc orders allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}