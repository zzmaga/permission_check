package com.example.demo.dto.role.response;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.entity.security.ScopeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private UUID id;
    private LocalizedText name;
    private String code;
    private ScopeType scopeType;
    private UUID organizationTypeId;
    private UUID organizationId;
    private String startMenuItemCode;
    private Set<UUID> permissions;
}

