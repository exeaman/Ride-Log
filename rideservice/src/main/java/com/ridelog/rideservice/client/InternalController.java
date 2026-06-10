package com.ridelog.rideservice.client;

import com.ridelog.rideservice.bike.BikeResponse;
import com.ridelog.rideservice.bike.BikeService;
import com.ridelog.rideservice.common.response.ApiResponseDto;
import com.ridelog.rideservice.ride.RideResponse;
import com.ridelog.rideservice.ride.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalController {

    private final BikeService bikeService;
    private final RideService rideService;

    @GetMapping("/bikes/{bikeId}")
    public ApiResponseDto<BikeResponse> getBikeById(
            @PathVariable Long bikeId
    ) {

        return ApiResponseDto.success(
                "Bike fetched successfully",
                bikeService.getBikeById(bikeId)
        );
    }

    @GetMapping("/bikes/user/{userId}")
    public ApiResponseDto<List<BikeResponse>> getBikesByUser(
            @PathVariable Long userId
    ) {

        return ApiResponseDto.success(
                "Bikes fetched successfully",
                bikeService.getAllBikesByUser(userId)
        );
    }

    @GetMapping("/rides/bike/{bikeId}")
    public ApiResponseDto<List<RideResponse>> getRidesByBike(
            @PathVariable Long bikeId
    ) {

        return ApiResponseDto.success(
                "Rides fetched successfully",
                rideService.getAllRidesByBikeInternal(
                        bikeId
                )
        );
    }
}