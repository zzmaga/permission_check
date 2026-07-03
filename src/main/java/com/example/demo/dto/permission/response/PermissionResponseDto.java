package com.example.demo.dto.permission.response;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.dto.common.ShortInfoWithCodeDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponseDto {
    private UUID id;
    private String code;
    private LocalizedText name;
    private LocalizedText description;

    private Boolean isDelegable;

    private Boolean isDangerous;

    private ShortInfoWithCodeDto group;
}
