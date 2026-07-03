package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDto {
    private UUID id;
    private OffsetDateTime createdAt;
    private UUID createdById;
    private OffsetDateTime updatedAt;
    private UUID updatedById;
    private OffsetDateTime deletedAt;
    private UUID deletedById;
    private Boolean isDeleted;
}