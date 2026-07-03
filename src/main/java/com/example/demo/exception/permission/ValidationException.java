package com.example.demo.exception.permission;

import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class ValidationException extends UserManagementException {
    public ValidationException(String message) {
        super(message, "BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
}
