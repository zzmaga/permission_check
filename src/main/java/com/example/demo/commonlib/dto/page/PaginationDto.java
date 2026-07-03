package com.example.demo.commonlib.dto.page;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDto {
    int page;
    int size;
    long totalItems;
    long totalPages;
}