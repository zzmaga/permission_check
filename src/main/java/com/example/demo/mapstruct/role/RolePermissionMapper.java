package com.example.demo.mapstruct.role;

import jakarta.persistence.EntityManager;
import com.example.demo.dto.role.RolePermissionDto;
import com.example.demo.dto.role.RolePermissionResponseDto;
import com.example.demo.entity.security.Permission;
import com.example.demo.entity.security.Role;
import com.example.demo.entity.security.RolePermission;
import com.example.demo.entity.security.ids.RolePermissionId;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = { RoleMapper.class,
        PermissionMapper.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RolePermissionMapper {
    RolePermissionResponseDto toResponseDto(RolePermission rolePermission);

    @Mapping(target = "id", ignore = true)
    RolePermission responseToEntity(RolePermissionResponseDto responseDto);

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "permission.id", target = "permissionId")
    RolePermissionDto toUniqueDto(RolePermission rolePermission);

    @Mapping(source = "roleId", target = "role", qualifiedByName = "roleRef")
    @Mapping(source = "permissionId", target = "permission", qualifiedByName = "permissionRef")
    @Mapping(target = "id", expression = "java(createId(dto.getRoleId(), dto.getPermissionId()))")
    RolePermission uniqueToEntity(RolePermissionDto dto, @Context EntityManager em);

    default RolePermission uniqueToEntity(RolePermissionDto dto) {
        if (dto == null) {
            return null;
        }
        return RolePermission.builder()
                .id(createId(dto.getRoleId(), dto.getPermissionId()))
                .build();
    }

    @Named("roleRef")
    default Role roleRef(UUID id, @Context EntityManager em) {
        if (id == null)
            return null;
        return em.getReference(Role.class, id);
    }

    @Named("permissionRef")
    default Permission permissionRef(UUID id, @Context EntityManager em) {
        if (id == null)
            return null;
        return em.getReference(Permission.class, id);
    }

    default RolePermissionId createId(UUID roleId, UUID permissionId) {
        return RolePermissionId.builder()
                .roleId(roleId)
                .permissionId(permissionId)
                .build();
    }
}

