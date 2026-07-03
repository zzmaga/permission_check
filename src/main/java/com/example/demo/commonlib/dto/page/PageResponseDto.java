package com.example.demo.commonlib.dto.page;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseDto<T> {
    private List<T> items;
    private PaginationDto pagination;
}