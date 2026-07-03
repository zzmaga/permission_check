package com.example.demo.dto.role.response;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.commonlib.dto.common.ShortInfoDto;
import com.example.demo.commonlib.dto.common.ShortInfoWithCodeDto;
import com.example.demo.entity.security.ScopeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleViewResponseDto {

    private UUID id;
    private LocalizedText name;
    private String code;
    private ScopeType scopeType;

    private ShortInfoDto organizationType;
    private ShortInfoDto organization;

    private String startMenuItemCode;

    private List<ShortInfoWithCodeDto> permissions;
}
