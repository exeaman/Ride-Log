package com.ridelog.analyticsservice.exception;

import com.ridelog.analyticsservice.common.response.ApiResponseDto;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND
                )
                .body(
                        ApiResponseDto.error(
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ApiResponseDto<Void>> handleFeignNotFound(
            FeignException.NotFound ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND
                )
                .body(
                        ApiResponseDto.error(
                                "Requested resource was not found"
                        )
                );
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleFeignException(
            FeignException ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.BAD_GATEWAY
                )
                .body(
                        ApiResponseDto.error(
                                "Unable to communicate with Ride Service"
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleGenericException(
            Exception ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        ApiResponseDto.error(
                                "An unexpected error occurred"
                        )
                );
    }
}
