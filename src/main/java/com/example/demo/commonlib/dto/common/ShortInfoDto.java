package com.example.demo.commonlib.dto.common;

import com.example.demo.commonlib.model.LocalizedText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ShortInfoDto {

    private UUID id;
    private LocalizedText name;
}