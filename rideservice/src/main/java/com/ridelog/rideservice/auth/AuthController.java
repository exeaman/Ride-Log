package com.ridelog.rideservice.auth;

import com.ridelog.rideservice.common.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication",
        description = """
                Authentication APIs for RideLog users.

                Features:
                • User Registration
                • User Login

                Security:
                • Passwords are stored using BCrypt hashing.
                • JWT authentication will be added in future versions.

                Validation Rules:
                • Email must be unique.
                • Password must contain at least 8 characters.
                """
)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = """
                    Creates a new RideLog account.

                    Business Rules:
                    • Email must be unique.
                    • Password must be at least 8 characters.
                    • Password is encrypted before storage.

                    Example:
                    name = Aman
                    email = aman@ridelog.com
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already registered"
            )
    })
    @PostMapping("/register")
    public ApiResponseDto<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return ApiResponseDto.success(
                "User registered successfully",
                authService.register(request)
        );
    }

    @Operation(
            summary = "Login user",
            description = """
                    Authenticates a registered RideLog user.

                    Returns:
                    • User details
                    • Authentication token (future JWT implementation)

                    Login fails when:
                    • Email does not exist
                    • Password is incorrect
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            )
    })
    @PostMapping("/login")
    public ApiResponseDto<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ApiResponseDto.success(
                "Login successful",
                authService.login(request)
        );
    }
}