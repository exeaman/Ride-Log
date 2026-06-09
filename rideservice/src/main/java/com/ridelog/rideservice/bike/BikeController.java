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

@RestController
@RequestMapping("/bikes")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    @PostMapping
    public ApiResponseDto<BikeResponse> createBike(
            @Valid @RequestBody CreateBikeRequest request
    ) {
        return ApiResponseDto.success(
                "Bike created successfully",
                bikeService.createBike(request)
        );
    }

    @GetMapping("/user/{userId}")
    public ApiResponseDto<List<BikeResponse>> getAllBikesByUser(
            @PathVariable Long userId
    ) {
        List<BikeResponse> bikes =
                bikeService.getAllBikesByUser(userId);

        String message =
                bikes.isEmpty()
                        ? "No bikes found for this user"
                        : "Bikes fetched successfully";

        return ApiResponseDto.success(message, bikes);
    }

    @GetMapping("/my")
    public ApiResponseDto<List<BikeResponse>> getMyBikes() {
        return ApiResponseDto.success(
                "Bikes fetched successfully",
                bikeService.getMyBikes()
        );
    }

    @PutMapping("/{bikeId}")
    public ApiResponseDto<BikeResponse> updateBike(
            @PathVariable Long bikeId,
            @Valid @RequestBody UpdateBikeRequest request
    ) {
        return ApiResponseDto.success(
                "Bike updated successfully",
                bikeService.updateBike(bikeId, request)
        );
    }

    @DeleteMapping("/{bikeId}")
    public ApiResponseDto<Void> deleteBike(
            @PathVariable Long bikeId
    ) {
        bikeService.deleteBike(bikeId);

        return ApiResponseDto.success(
                "Bike archived successfully"
        );
    }
}