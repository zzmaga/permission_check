package com.example.demo.service.impl;

import com.example.demo.commonlib.dto.common.ShortInfoWithCodeDto;
import com.example.demo.commonlib.dto.page.PageResponseDto;
import com.example.demo.commonlib.dto.page.PaginationDto;
import com.example.demo.commonlib.dto.response.ApiResponseDto;
import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.dto.permission.request.PermissionCreateRequestDto;
import com.example.demo.dto.permission.request.PermissionUpdateRequestDto;
import com.example.demo.dto.permission.response.PermissionResponseDto;
import com.example.demo.dto.security.PermissionDto;
import com.example.demo.entity.security.Permission;
import com.example.demo.entity.security.PermissionGroup;
import com.example.demo.exception.permission.PermissionAlreadyExistsException;
import com.example.demo.exception.permission.PermissionAssignedToRolesException;
import com.example.demo.exception.permission.PermissionNotFoundException;
import com.example.demo.exception.permission.ValidationException;
import com.example.demo.filter.PermissionFilter;
import com.example.demo.mapstruct.role.PermissionMapper;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionGroupLookupService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RolePermissionService;
import com.example.demo.util.SecurityUtil;
import com.example.demo.validation.PermissionsValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor // Lombok генерирует конструктор, через который Spring внедряет (Inject) зависимости.
public class PermissionServiceImpl implements PermissionService {
    // Зависимости (Бины)
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper; // Entity -> DTO
    private final PermissionGroupLookupService permissionGroupLookupService;
    private final RolePermissionService rolePermissionService;

    @Override
    @Transactional(readOnly = true) // открывает транзакцию в режиме "только чтение". Это оптимизирует работу Hibernate (он не будет тратить ресурсы на проверку изменений в объектах).
    public PermissionDto findByCode(String code) {
        Permission permission = permissionRepository.findByCodeIgnoreCaseAndIsDeletedFalse(code)
                .orElseThrow(
                        () -> new PermissionNotFoundException("Permission not found!")
                );
           return permissionMapper.toUniqueDto(permission);
    }

    @Transactional(readOnly = true)
    @Override
    public PermissionDto findById(UUID id) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(
                        () -> new PermissionNotFoundException("Permission not found!")
                );
        return permissionMapper.toUniqueDto(permission);
    }

    public boolean checkExistByPermissionGroupId(UUID permissionGroupId) {
        return permissionRepository.existsByGroupIdAndIsDeletedFalse(permissionGroupId);
    }

    public List<Permission> findExistsByIds(Set<UUID> permissions) {
        return permissionRepository.findAllByIdInAndIsDeletedFalse(permissions);
    }

    @Override
    public List<Permission> findAllByRoleId(UUID roleId) {
        return permissionRepository.findAllByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<PermissionResponseDto> getAllPermissions(PermissionsValidator validData) {
        Pageable pageable = validData.toPageable();

        Page<Permission> page = permissionRepository.findAll(
                PermissionFilter.build(validData),
                pageable
        );
        // Pattern Builder
        return PageResponseDto.<PermissionResponseDto>builder()
                .items(page.getContent().stream()
                        .map(this::toPermissionResponseDto)
                        .toList())
                .pagination(PaginationDto.builder()
                        .page(page.getNumber() + 1)
                        .size(page.getSize())
                        .totalItems(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .build())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponseDto<PermissionResponseDto> getPermissionById(UUID id) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found!"));

        return ApiResponseDto.<PermissionResponseDto>builder()
                .result(toPermissionResponseDto(permission))
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<PermissionResponseDto> createPermission(PermissionCreateRequestDto request) {
        validateLocalizedName(request.getName());

        if (permissionRepository.existsByCodeIgnoreCaseAndIsDeletedFalse(request.getCode())) {
            throw new PermissionAlreadyExistsException("Permission with code already exists");
        }

        PermissionGroup group = permissionGroupLookupService.findActiveEntityById(request.getGroupId());

        // Pattern Builder
        Permission permission = Permission.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .group(group)
                .isDelegable(Boolean.TRUE.equals(request.getIsDelegable()))
                .isDangerous(Boolean.TRUE.equals(request.getIsDangerous()))
                .build();

        permission.setCreatedBy(SecurityUtil.getCurrentUserId());

        return ApiResponseDto.<PermissionResponseDto>builder()
                .result(toPermissionResponseDto(permissionRepository.save(permission)))
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<PermissionResponseDto> updatePermission(UUID id, PermissionUpdateRequestDto request) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found!"));

        if (request.getName() != null) {
            validateLocalizedName(request.getName());
            permission.setName(request.getName());
        }

        if (request.getDescription() != null) {
            permission.setDescription(request.getDescription());
        }

        if (request.getGroupId() != null) {
            PermissionGroup group = permissionGroupLookupService.findActiveEntityById(request.getGroupId());
            permission.setGroup(group);
        }

        if (request.getIsDelegable() != null) {
            permission.setDelegable(request.getIsDelegable());
        }

        if (request.getIsDangerous() != null) {
            permission.setDangerous(request.getIsDangerous());
        }

        permission.setUpdatedBy(SecurityUtil.getCurrentUserId());

        return ApiResponseDto.<PermissionResponseDto>builder()
                .result(toPermissionResponseDto(permissionRepository.save(permission)))
                .build();
    }

    @Override
    @Transactional
    public void deletePermission(UUID id) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found!"));

        if (rolePermissionService.existsByPermissionId(id)) {
            throw new PermissionAssignedToRolesException("Permission is assigned to roles");
        }

        permission.setIsDeleted(true);
        permission.setDeletedAt(OffsetDateTime.now());
        permission.setDeletedBy(SecurityUtil.getCurrentUserId());

        permissionRepository.save(permission);
    }

    private PermissionResponseDto toPermissionResponseDto(Permission permission) {
        PermissionGroup group = permission.getGroup();

        return PermissionResponseDto.builder()
                .id(permission.getId())
                .code(permission.getCode())
                .name(permission.getName())
                .description(permission.getDescription())
                .isDelegable(permission.isDelegable())
                .isDangerous(permission.isDangerous())
                .group(group == null ? null : ShortInfoWithCodeDto.builder()
                        .id(group.getId())
                        .code(group.getCode())
                        .name(group.getName())
                        .build())
                .build();
    }

    private void validateLocalizedName(LocalizedText text) {
        if (text == null) {
            throw new ValidationException("name must contain at least one localized value");
        }

        boolean hasValue = hasText(text.getRu())
                || hasText(text.getKk())
                || hasText(text.getEn());

        if (!hasValue) {
            throw new ValidationException("name must contain at least one localized value");
        }
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
