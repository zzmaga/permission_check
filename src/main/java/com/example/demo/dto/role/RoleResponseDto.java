package com.example.demo.dto.role;

import com.example.demo.dto.security.LocalizedTextDto;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponseDto {
    private UUID roleId;

    private LocalizedTextDto name;

    private String code;

    private LocalizedTextDto description;

    private UUID organizationTypeId;

    private UUID organizationId;

    private Boolean isMain;

    private String startMenuItemCode;

    private String scopeType;

    private Set<String> permissionCodes;

    private Boolean isDeleted;

    private Boolean isVisible;
}