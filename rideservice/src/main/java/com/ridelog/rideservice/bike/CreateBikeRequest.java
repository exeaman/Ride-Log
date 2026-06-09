package com.ridelog.rideservice.bike;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBikeRequest {

    @NotBlank(message = "Registration number is required")
    @Size(max = 20, message = "Registration number cannot exceed 20 characters")
    private String registrationNumber;

    @NotBlank(message = "Registration number is required")
    private String confirmRegistrationNumber;

    @Size(max = 50, message = "Chassis number cannot exceed 50 characters")
    private String chassisNumber;

    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @Min(value = 1900)
    @Max(value = 2100)
    private Integer year;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;
}