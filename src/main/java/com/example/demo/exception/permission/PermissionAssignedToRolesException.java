package com.example.demo.exception.permission;

import com.example.demo.exception.core.UserManagementException;
import org.springframework.http.HttpStatus;

public class PermissionAssignedToRolesException extends UserManagementException {
    public PermissionAssignedToRolesException(String message) {
        super(message, HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT);
    }
}