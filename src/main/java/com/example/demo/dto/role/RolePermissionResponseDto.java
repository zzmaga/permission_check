package com.example.demo.dto.role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionResponseDto {
    private RoleResponseDto role;

    private PermissionResponseDto permission;
}
