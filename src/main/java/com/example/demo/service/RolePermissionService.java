package com.example.demo.service;

import com.example.demo.dto.role.RolePermissionDto;
import com.example.demo.entity.security.RolePermission;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RolePermissionService {
    List<RolePermissionDto> findAllByRoleId(UUID roleId);

    Set<UUID> saveRolePermissions(List<RolePermission> rolePermissions);

    void deleteByRoleId(UUID roleId);

    boolean existsByPermissionId(UUID permissionId);
}
