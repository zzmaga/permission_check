package com.example.demo.exception.permission;

import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class PermissionAlreadyExistsException extends UserManagementException {
    public PermissionAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT);
    }
}
