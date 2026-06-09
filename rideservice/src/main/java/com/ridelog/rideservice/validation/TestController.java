package com.ridelog.rideservice.validation;

import com.ridelog.rideservice.auth.user.User;
import com.ridelog.rideservice.auth.user.UserRepository;
import com.ridelog.rideservice.auth.user.UserService;
import com.ridelog.rideservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserService userService;
    @GetMapping("/test")
    public String test() {
        return "Ride Service Working";
    }

    /*
    @GetMapping("/test-token")
    public String testToken() {

        User user =
                userRepository.findByEmail(
                        "jioaman100@gmail.com"
                ).orElseThrow();

        return jwtService.generateToken(user);
    }*/
    @GetMapping("/me")
    public String me() {

        return userService
                .getCurrentUser()
                .getEmail();
    }
}