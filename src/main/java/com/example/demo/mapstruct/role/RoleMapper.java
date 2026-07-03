package com.example.demo.mapstruct.role;

import com.example.demo.dto.role.request.RoleCreateRequestDto;
import com.example.demo.dto.role.request.RoleUpdateRequestDto;
import com.example.demo.dto.role.response.RoleViewResponseDto;
import com.example.demo.entity.security.Permission;
import com.example.demo.entity.security.Role;
import org.mapstruct.*;
import com.example.demo.dto.role.response.RoleResponseDto;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {LocalizedTextMapper.class },
        imports = {Permission.class, Collectors.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

//    @Mapping(source = "id", target = "roleId")
//    @Mapping(source = "scopeType", target = "scopeType", qualifiedByName = "scopeTypeToString")
//    @Mapping(source = "permissions", target = "permissionCodes", qualifiedByName = "permissionsToCodeSet")
//    RoleResponseDto toResponseDto(Role role);

    @Mapping(
            target = "permissions",
            expression = "java(role.getPermissions() != null ? role.getPermissions().stream().map(Permission::getId).collect(Collectors.toSet()) : null)"
    )
    RoleResponseDto toResponseDto(Role role);

    @Mapping(target = "permissions", source = "permissionIds")
    RoleResponseDto toResponseDto(Role role, Set<UUID> permissionIds);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "scopeType", target = "scopeType", qualifiedByName = "stringToScopeType")
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Role responseToEntity(RoleResponseDto responseDto);

    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "isVisible", ignore = true)
    Role requestToEntity(RoleCreateRequestDto dto);

    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "organizationType", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    RoleViewResponseDto toRoleViewResponseDto(Role role);

    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "organizationId", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "code", ignore = true)
    void updateRole(@MappingTarget Role role, RoleUpdateRequestDto dto);

    @Named("scopeTypeToString")
    default String scopeTypeToString(com.example.demo.entity.security.ScopeType scopeType) {
        return scopeType != null ? scopeType.name() : null;
    }

    @Named("stringToScopeType")
    default com.example.demo.entity.security.ScopeType stringToScopeType(String scopeType) {
        return scopeType != null ? com.example.demo.entity.security.ScopeType.valueOf(scopeType) : null;
    }

    @Named("permissionsToCodeSet")
    default Set<String> permissionsToCodeSet(Set<Permission> permissions) {
        if (permissions == null || permissions.isEmpty())
            return Collections.emptySet();
        return permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toSet());
    }

}
