package com.ridelog.rideservice.bike;


import com.ridelog.rideservice.common.response.ApiResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(
        name = "Bike Management",
        description = """
                APIs for managing motorcycles owned by a user.

                Features:
                - Register a new bike
                - Retrieve bike details
                - Retrieve all bikes belonging to a user
                - Update bike information
                - Remove a bike from the system

                Registration Number Rules:
                - Must be unique per user
                - Supports traditional Indian registrations
                - Supports BH-series registrations
                - Cannot be modified after bike creation

                Example:
                MH12AB1234
                26BH1234AA
                """
)
@RestController
@RequestMapping("/bikes")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    @Operation(
            summary = "Register a new bike",
            description = """
                Creates a new bike for the specified user.

                Business Rules:
                - User must exist
                - Registration number must be valid
                - Registration number and confirmation must match
                - Registration number must be unique for the user

                Registration numbers are immutable after creation.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bike created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration number or validation failure"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "Bike already exists")
    })
    @PostMapping("/user/{userId}")
    public ApiResponseDto<BikeResponse> createBike(@PathVariable Long userId, @Valid @RequestBody CreateBikeRequest request) {

        return ApiResponseDto.success("Bike created successfully", bikeService.createBike(userId, request));
    }

    @Operation(
            summary = "Get bike by id",
            description = """
                Retrieves complete details of a bike.

                Returns:
                - Registration number
                - Chassis number
                - Brand
                - Model
                - Manufacturing year
                - Purchase date
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bike found"),
            @ApiResponse(responseCode = "404", description = "Bike not found")
    })
    @GetMapping("/{bikeId}")
    public ApiResponseDto<BikeResponse> getBikeById(@PathVariable Long bikeId) {

        return ApiResponseDto.success("Bike fetched successfully", bikeService.getBikeById(bikeId));
    }

    @Operation(
            summary = "Get all bikes owned by a user",
            description = """
                Returns all bikes associated with a user.

                Useful for:
                - Dashboard screens
                - Bike selection before ride creation
                - Profile overview
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bikes fetched successfully / No bikes found for this user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/user/{userId}")
    public ApiResponseDto<List<BikeResponse>> getAllBikesByUser(@PathVariable Long userId) {
        List<BikeResponse> bikes =
                bikeService.getAllBikesByUser(userId);

        String message =
                bikes.isEmpty()
                        ? "No bikes found for this user"
                        : "Bikes fetched successfully";

        return ApiResponseDto.success(
                message,
                bikes
        );
    }

    @Operation(
            summary = "Update bike information",
            description = """
                Updates mutable bike information.

                Allowed:
                - Brand
                - Model
                - Manufacturing year
                - Purchase date

                Not Allowed:
                - Registration number
                - Chassis number

                These fields are treated as the bike's identity
                and cannot be changed after creation.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bike updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Bike not found")
    })
    @PutMapping("/{bikeId}")
    public ApiResponseDto<BikeResponse> updateBike(@PathVariable Long bikeId, @Valid @RequestBody UpdateBikeRequest request) {

        return ApiResponseDto.success("Bike updated successfully", bikeService.updateBike(bikeId, request));
    }

    @Operation(
            summary = "Delete a bike",
            description = """
                Permanently removes a bike from the system.

                Warning:
                This operation cannot be undone.

                Future versions may prevent deletion
                if rides already exist for the bike.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bike deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bike not found")
    })
    @DeleteMapping("/{bikeId}")
    public ApiResponseDto<Void> deleteBike(@PathVariable Long bikeId) {

        bikeService.deleteBike(bikeId);

        return ApiResponseDto.success("Bike deleted successfully");
    }
}