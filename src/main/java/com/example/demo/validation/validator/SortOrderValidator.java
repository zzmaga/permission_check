package com.example.demo.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.demo.validation.annotation.ValidSortOrder;

public class SortOrderValidator implements ConstraintValidator<ValidSortOrder, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        if (s != null && !s.equalsIgnoreCase("asc") && !s.equalsIgnoreCase("desc")) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Sort order allowed only: asc, desc").addConstraintViolation();
            return false;
        }
        return true;
    }
}
