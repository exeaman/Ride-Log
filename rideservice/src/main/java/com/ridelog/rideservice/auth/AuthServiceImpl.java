package com.ridelog.rideservice.auth;

import com.ridelog.rideservice.auth.user.User;
import com.ridelog.rideservice.auth.user.UserMapper;
import com.ridelog.rideservice.auth.user.UserRepository;
import com.ridelog.rideservice.exception.DuplicateResourceException;
import com.ridelog.rideservice.exception.UnauthorizedException;
import com.ridelog.rideservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        validateEmailAvailability(email);

        User user = User.builder().name(request.getName()).email(email).password(passwordEncoder.encode(request.getPassword())).createdAt(LocalDateTime.now()).build();

        User savedUser = userRepository.save(user);

        return buildAuthResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail()
                .trim()
                .toLowerCase();
        User user = getUserByEmailOrThrow(email);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        return buildAuthResponse(user);
    }

    private void validateEmailAvailability(String email) {

        if (userRepository.existsByEmail(email)) {

            throw new DuplicateResourceException("Email already registered");
        }
    }

    private User getUserByEmailOrThrow(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
    }

    private AuthResponse buildAuthResponse(User user) {
        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .user(userMapper.toResponse(user))
                .build();
    }
}