package com.ridelog.rideservice.validation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Ride Service Working";
    }
}