package com.example.demo.dto.role;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionDto {
    private UUID roleId;
    private UUID permissionId;
}
