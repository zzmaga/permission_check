package com.example.demo.dto.role.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateRequestDto {

    @NotNull(message = "Name must not be null")
    @ValidLocalizedText
    private LocalizedText name;

    @NotBlank(message = "Code must not be blank")
    private String code;

    @NotNull(message = "Scope Type must not be null")
    private ScopeType scopeType;

    private UUID organizationTypeId;
    private UUID organizationId;

    @Size(min = 1, max = 255, message = "Start Menu Item Code must be between 1 and 255 characters")
    private String startMenuItemCode;

    @NotEmpty(message = "Permission IDs must not be empty")
    private Set<@NotNull(message = "Permission ID cannot be null") UUID> permissionIds;
}