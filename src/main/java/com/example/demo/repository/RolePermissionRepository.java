package com.example.demo.repository;

import com.example.demo.entity.security.Role;
import com.example.demo.entity.security.RolePermission;
import com.example.demo.entity.security.ids.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
    List<RolePermission> findAllByRoleId(UUID roleId);

    void deleteByRoleId(UUID roleId);
    boolean existsByPermissionId(UUID permissionId);
}

