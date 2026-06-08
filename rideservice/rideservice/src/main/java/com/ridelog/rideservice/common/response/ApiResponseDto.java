package com.ridelog.rideservice.common.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {

    private boolean success;

    private String message;

    private T data;

    private LocalDateTime timestamp;
    public static <T> ApiResponseDto<T> success(
            String message,
            T data
    ) {
        return ApiResponseDto.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
    public static ApiResponseDto<Void> success(
            String message
    ) {
        return ApiResponseDto.<Void>builder()
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}