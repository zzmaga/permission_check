package com.example.demo.service;

import jakarta.validation.constraints.NotNull;
import com.example.demo.entity.security.PermissionGroup;

import java.util.UUID;

public interface PermissionGroupLookupService {
    PermissionGroup findActiveEntityById(@NotNull UUID id);
}