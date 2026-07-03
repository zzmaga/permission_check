package com.example.demo.exception.permission;

import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class PermissionGroupNotFoundException extends UserManagementException {
    public PermissionGroupNotFoundException() {
        super(
                "PermissionGroup not found",
                "NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
