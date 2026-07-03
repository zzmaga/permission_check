package com.example.demo.dto.permission.request;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.validation.annotation.ValidLocalizedText;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionUpdateRequestDto {

    @ValidLocalizedText
    private LocalizedText name;

    @ValidLocalizedText
    private LocalizedText description;

    private UUID groupId;

    private Boolean isDelegable;

    private Boolean isDangerous;
}
