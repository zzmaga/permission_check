package com.example.demo.service.impl;

import com.example.demo.dto.role.RolePermissionDto;
import com.example.demo.entity.security.RolePermission;
import com.example.demo.mapstruct.role.RolePermissionMapper;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

@Service
@AllArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionDto> findAllByRoleId(UUID roleId) {
        return rolePermissionRepository.findAllByRoleId(roleId).stream()
                .map(rolePermissionMapper::toUniqueDto)
                .toList();
    }

    @Override
    @Transactional
    public Set<UUID> saveRolePermissions(List<RolePermission> rolePermissions) {
        return rolePermissionRepository.saveAll(rolePermissions).stream()
                .map(rp -> rp.getPermission().getId())
                .collect(toSet());
    }

    @Override
    @Transactional
    public void deleteByRoleId(UUID roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPermissionId(UUID permissionId) {
        return rolePermissionRepository.existsByPermissionId(permissionId);
    }
}