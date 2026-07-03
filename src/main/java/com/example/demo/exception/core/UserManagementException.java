package com.example.demo.exception.core;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserManagementException extends RuntimeException {
    private final String code;
    private final HttpStatus status;


    public UserManagementException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
