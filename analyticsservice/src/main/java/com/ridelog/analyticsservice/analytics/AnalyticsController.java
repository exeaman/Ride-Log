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
@Tag(
        name = "Analytics",
        description = """
                Analytics APIs for RideLog.

                Features:
                • Bike Analytics
                • User Analytics

                Data is aggregated from Ride Service
                using Feign Client communication.
                """
)
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Operation(
            summary = "Get bike analytics"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Analytics generated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bike not found"
            )
    })
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

    @Operation(
            summary = "Get user analytics"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Analytics generated successfully"
            )
    })
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
