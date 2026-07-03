package com.example.demo.commonlib.dto.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.demo.commonlib.model.LocalizedText;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonPropertyOrder({"id", "code", "name"})
public class ShortInfoWithCodeDto extends ShortInfoDto {

    private String code;

    public ShortInfoWithCodeDto(UUID id, String code, LocalizedText name) {
        super(id, name);
        this.code = code;
    }

}
