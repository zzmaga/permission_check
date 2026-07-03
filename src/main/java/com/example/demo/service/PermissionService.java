package com.example.demo.service;

import com.example.demo.commonlib.dto.page.PageResponseDto;
import com.example.demo.commonlib.dto.response.ApiResponseDto;
import com.example.demo.dto.permission.request.PermissionCreateRequestDto;
import com.example.demo.dto.permission.request.PermissionUpdateRequestDto;
import com.example.demo.dto.permission.response.PermissionResponseDto;
import com.example.demo.dto.security.PermissionDto;
import com.example.demo.entity.security.Permission;
import com.example.demo.validation.PermissionsValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PermissionService {
    PermissionDto findByCode(String code);

    PermissionDto findById(UUID id);

    boolean checkExistByPermissionGroupId(UUID permissionGroupId);

    List<Permission> findExistsByIds(Set<UUID> permissions);

    List<Permission> findAllByRoleId(UUID roleId);

    PageResponseDto<PermissionResponseDto> getAllPermissions(PermissionsValidator validData);

    ApiResponseDto<PermissionResponseDto> getPermissionById(UUID id);

    ApiResponseDto<PermissionResponseDto> createPermission(PermissionCreateRequestDto request);

    ApiResponseDto<PermissionResponseDto> updatePermission(UUID id, PermissionUpdateRequestDto request);

    void deletePermission(UUID id);
}
