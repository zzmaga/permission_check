package com.example.demo.service.impl;

import jakarta.validation.constraints.NotNull;
import com.example.demo.entity.security.PermissionGroup;
import com.example.demo.exception.permission.PermissionGroupNotFoundException;
import com.example.demo.repository.PermissionGroupRepository;
import com.example.demo.service.PermissionGroupLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionGroupLookupServiceImpl implements PermissionGroupLookupService {

    private final PermissionGroupRepository permissionGroupRepository;

    @Override
    public PermissionGroup findActiveEntityById(@NotNull UUID id) {
        return permissionGroupRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(PermissionGroupNotFoundException::new);
    }
}