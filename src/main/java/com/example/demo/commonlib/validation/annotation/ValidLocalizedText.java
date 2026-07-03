package com.example.demo.commonlib.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.example.demo.commonlib.validation.validator.LocalizedTextValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalizedTextValidator.class)
public @interface ValidLocalizedText {

    String message() default "Invalid localized text";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
