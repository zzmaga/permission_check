package com.example.demo.mapstruct.role;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.dto.security.LocalizedTextDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface LocalizedTextMapper {
    LocalizedTextDto toDto(LocalizedText localizedText);
    LocalizedText toEntity(LocalizedTextDto localizedTextDto);
}
