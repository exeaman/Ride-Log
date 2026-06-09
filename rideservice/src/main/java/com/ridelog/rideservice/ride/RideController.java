package com.ridelog.rideservice.ride;

import com.ridelog.rideservice.common.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ride Management", description = """
        APIs for managing motorcycle rides.
        
        Features:
        • Create ride entries
        • Retrieve ride history
        • Update ride details
        • Delete rides
        
        Business Rules:
        • Bike must exist
        • Ride date cannot be before bike purchase date
        • Ride date cannot be in the future
        • End odometer must be greater than start odometer
        • Overlapping rides are allowed
        • Distance is automatically calculated
        """)
@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @Operation(summary = "Create a ride", description = """
            Creates a new ride entry.
            
            Distance is automatically calculated using:
            
            distance = endOdometer - startOdometer
            
            Validation:
            • Bike must exist
            • Ride date must be valid
            • End odometer must be greater than start odometer
            """)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Ride created successfully"), @ApiResponse(responseCode = "400", description = "Validation failed"), @ApiResponse(responseCode = "404", description = "Bike not found")})
    @PostMapping
    public ApiResponseDto<RideResponse> createRide(@Valid @RequestBody CreateRideRequest request) {

        return ApiResponseDto.success("Ride created successfully", rideService.createRide(request));
    }

    @Operation(summary = "Get ride by id", description = """
            Retrieves complete ride information.
            
            Returns:
            • Ride details
            • Bike reference
            • Distance travelled
            """)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Ride found"), @ApiResponse(responseCode = "404", description = "Ride not found")})
    @GetMapping("/{rideId}")
    public ApiResponseDto<RideResponse> getRideById(@PathVariable Long rideId) {

        return ApiResponseDto.success("Ride fetched successfully", rideService.getRideById(rideId));
    }

    @Operation(summary = "Get all rides for a bike", description = """
            Returns all rides associated with a bike.
            
            Empty list is returned when:
            • Bike exists
            • No rides have been recorded
            
            Error is returned when:
            • Bike does not exist
            """)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Rides retrieved successfully"), @ApiResponse(responseCode = "404", description = "Bike not found")})
    @GetMapping("/bike/{bikeId}")
    public ApiResponseDto<List<RideResponse>> getAllRidesByBike(@PathVariable Long bikeId) {

        List<RideResponse> rides = rideService.getAllRidesByBike(bikeId);

        String message = rides.isEmpty() ? "No rides found for this bike" : "Rides fetched successfully";

        return ApiResponseDto.success(message, rides);
    }

    @Operation(summary = "Update ride", description = """
            Updates mutable ride information.
            
            Allowed:
            • Title
            • Ride Date
            • Start Odometer
            • End Odometer
            • Notes
            
            Not Allowed:
            • Bike Association
            
            Distance is recalculated automatically.
            """)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Ride updated successfully"), @ApiResponse(responseCode = "400", description = "Validation failed"), @ApiResponse(responseCode = "404", description = "Ride not found")})
    @PutMapping("/{rideId}")
    public ApiResponseDto<RideResponse> updateRide(@PathVariable Long rideId, @Valid @RequestBody UpdateRideRequest request) {

        return ApiResponseDto.success("Ride updated successfully", rideService.updateRide(rideId, request));
    }

    @Operation(summary = "Delete ride", description = """
            Permanently removes a ride entry.
            
            Warning:
            This operation cannot be undone.
            """)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Ride deleted successfully"), @ApiResponse(responseCode = "404", description = "Ride not found")})
    @DeleteMapping("/{rideId}")
    public ApiResponseDto<Void> deleteRide(@PathVariable Long rideId) {

        rideService.deleteRide(rideId);

        return ApiResponseDto.success("Ride deleted successfully");
    }
}