package com.ridelog.analyticsservice.analytics;

import com.ridelog.analyticsservice.analytics.bike.BikeAnalyticsResponse;
import com.ridelog.analyticsservice.analytics.user.UserAnalyticsResponse;
import com.ridelog.analyticsservice.common.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/bike/{bikeId}")
    public ApiResponseDto<BikeAnalyticsResponse> getBikeAnalytics(
            @PathVariable Long bikeId
    ) {

        return ApiResponseDto.success(
                "Bike analytics generated successfully",
                analyticsService.getBikeAnalytics(
                        bikeId
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ApiResponseDto<UserAnalyticsResponse> getUserAnalytics(
            @PathVariable Long userId
    ) {

        return ApiResponseDto.success(
                "User analytics generated successfully",
                analyticsService.getUserAnalytics(
                        userId
                )
        );
    }
}