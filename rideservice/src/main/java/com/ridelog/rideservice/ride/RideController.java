package com.ridelog.rideservice.ride;

import com.ridelog.rideservice.common.response.ApiResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping
    public ApiResponseDto<RideResponse> createRide(
            @Valid @RequestBody CreateRideRequest request
    ) {

        return ApiResponseDto.success(
                "Ride created successfully",
                rideService.createRide(request)
        );
    }

    @GetMapping("/bike/{bikeId}")
    public ApiResponseDto<List<RideResponse>> getAllRidesByBike(
            @PathVariable Long bikeId
    ) {

        List<RideResponse> rides =
                rideService.getAllRidesByBike(bikeId);

        String message =
                rides.isEmpty()
                        ? "No rides found for this bike"
                        : "Rides fetched successfully";

        return ApiResponseDto.success(
                message,
                rides
        );
    }

    @PutMapping("/{rideId}")
    public ApiResponseDto<RideResponse> updateRide(
            @PathVariable Long rideId,
            @Valid @RequestBody UpdateRideRequest request
    ) {

        return ApiResponseDto.success(
                "Ride updated successfully",
                rideService.updateRide(
                        rideId,
                        request
                )
        );
    }

    @DeleteMapping("/{rideId}")
    public ApiResponseDto<Void> deleteRide(
            @PathVariable Long rideId
    ) {

        rideService.deleteRide(rideId);

        return ApiResponseDto.success(
                "Ride deleted successfully"
        );
    }
}