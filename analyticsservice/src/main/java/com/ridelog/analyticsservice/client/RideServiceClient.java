package com.ridelog.analyticsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.ridelog.analyticsservice.client.dto.BikeResponse;
import com.ridelog.analyticsservice.client.dto.RideResponse;
import com.ridelog.analyticsservice.common.response.ApiResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RIDESERVICE")
public interface RideServiceClient {

    @GetMapping("/internal/bikes/{bikeId}")
    ApiResponseDto<BikeResponse> getBikeById(
            @PathVariable Long bikeId
    );

    @GetMapping("/internal/bikes/user/{userId}")
    ApiResponseDto<List<BikeResponse>> getBikesByUser(
            @PathVariable Long userId
    );

    @GetMapping("/internal/rides/bike/{bikeId}")
    ApiResponseDto<List<RideResponse>> getRidesByBike(
            @PathVariable Long bikeId
    );
}