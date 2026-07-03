package com.example.demo.dto.role;

import com.example.demo.dto.security.LocalizedTextDto;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponseDto {
    private UUID id;

    private UUID groupId;

    private String groupCode;

    private LocalizedTextDto groupName;

    private LocalizedTextDto name;

    private String code;

    private LocalizedTextDto description;

    private Boolean isDelegable;

    private Boolean isDangerous;

    private Boolean isVisible;

    private Boolean isDeleted;

    private Boolean isBlocked;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
