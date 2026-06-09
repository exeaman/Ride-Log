package com.ridelog.rideservice.auth;

import com.ridelog.rideservice.auth.user.UserResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    private UserResponse user;
}