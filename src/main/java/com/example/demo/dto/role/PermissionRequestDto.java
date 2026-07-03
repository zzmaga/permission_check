package com.example.demo.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.demo.dto.security.LocalizedTextDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequestDto {

    @NotNull(message = "Group ID is required")
    private UUID groupId;

    @NotNull(message = "Name is required")
    private LocalizedTextDto name;

    @NotBlank(message = "Code is required")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    private String code;

    private LocalizedTextDto description;

    @Builder.Default
    private Boolean isDelegable = false;

    @Builder.Default
    private Boolean isDangerous = false;
}
