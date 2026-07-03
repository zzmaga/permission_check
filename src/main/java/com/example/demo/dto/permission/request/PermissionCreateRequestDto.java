package com.example.demo.dto.permission.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.validation.annotation.ValidLocalizedText;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionCreateRequestDto {
    @NotBlank(message = "Code is required")
    @Size(max = 255, message = "Code must not exceed 255 characters")
    private String code;

    @NotNull(message = "Name is required")
    @ValidLocalizedText
    private LocalizedText name;

    @ValidLocalizedText
    private LocalizedText description;

    @NotNull(message = "Group ID is required")
    private UUID groupId;

    @NotNull(message = "isDelegable is required")
    private Boolean isDelegable;

    @NotNull(message = "isDangerous is required")
    private Boolean isDangerous;

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
