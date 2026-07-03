package com.example.demo.exception.permission;


import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class PermissionNotFoundException extends UserManagementException {
    public PermissionNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND);
    }
}