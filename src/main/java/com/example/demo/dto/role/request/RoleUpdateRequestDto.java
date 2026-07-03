package com.example.demo.dto.role.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.validation.annotation.ValidLocalizedText;
import com.example.demo.entity.security.ScopeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateRequestDto {

    @ValidLocalizedText
    private LocalizedText name;

    @Size(min = 1, max = 255, message = "Code must be between 1 and 255 characters")
    private String code;

    private ScopeType scopeType;
    private UUID organizationTypeId;

    @Size(min = 1, max = 255, message = "Start Menu Item Code must be between 1 and 255 characters")
    private String startMenuItemCode;

    @Size(min = 1, message = "Permission IDs must not be empty")
    private Set<@NotNull(message = "Permission ID cannot be null") UUID> permissionIds;
}