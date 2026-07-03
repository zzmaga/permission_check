package com.example.demo.dto.security;

import com.example.demo.commonlib.model.LocalizedText;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {

    private UUID id;

    private UUID groupId;

    private LocalizedText name;

    private String code;

    private LocalizedTextDto description;

    private Boolean isDelegable;

    private Boolean isDangerous;

    private Boolean isDeleted;

    private Boolean isVisible;

    private Boolean isBlocked;

    private OffsetDateTime createdAt;
    private UUID createdById;
    private OffsetDateTime updatedAt;
    private UUID updatedById;
    private OffsetDateTime deletedAt;
    private UUID deletedById;
}