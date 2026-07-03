package com.example.demo.exception.auth;

import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends UserManagementException {
    public UnauthorizedException() {
        super("User is not authorized", HttpStatus.UNAUTHORIZED.name(), HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED.name(), HttpStatus.UNAUTHORIZED);
    }
}

