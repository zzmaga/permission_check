package com.example.demo.commonlib.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.validation.annotation.ValidLocalizedText;
import org.springframework.util.StringUtils;


public class LocalizedTextValidator implements ConstraintValidator<ValidLocalizedText, LocalizedText> {

    @Override
    public boolean isValid(LocalizedText value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (!StringUtils.hasText(value.getRu())) {
            addViolation(context, "name must contain at least 'ru' localized value");
            return false;
        }

        if (!value.getRu().equals(value.getRu().strip()) || value.getRu().strip().length() < 3) {
            addViolation(context, "ru localized value must be at least 3 characters long and must not contain spaces at the beginning or end");
            return false;
        }

        if (value.getKk() != null) {
            if (!value.getKk().equals(value.getKk().strip()) || value.getKk().strip().length() < 3) {
                addViolation(context, "kk localized value must be at least 3 characters long and must not contain spaces at the beginning or end");
                return false;
            }
        }

        if (value.getEn() != null) {
            if (!value.getEn().equals(value.getEn().strip()) || value.getEn().strip().length() < 3) {
                addViolation(context, "en localized value must be at least 3 characters long and must not contain spaces at the beginning or end");
                return false;
            }
        }

        return true;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
