package com.ridelog.rideservice.exception;

import com.ridelog.rideservice.common.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(
            DuplicateResourceException ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @ExceptionHandler(InvalidRegistrationNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRegistrationNumberException(
            InvalidRegistrationNumberException ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(
            UnauthorizedException ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
    @ExceptionHandler(InvalidRideException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRideException(
            InvalidRideException ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        List<String> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message("Validation Failed")
                        .errors(errors)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex
    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .message("Internal Server Error")
                        .errors(List.of(ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}