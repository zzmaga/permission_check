package com.example.demo.commonlib.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {
    private T result;

    public static <T> ApiResponseDto<T> success(T result) {
        return new ApiResponseDto<>(result);
    }
}
