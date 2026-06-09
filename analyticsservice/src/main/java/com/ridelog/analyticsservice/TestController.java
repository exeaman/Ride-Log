package com.ridelog.analyticsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Analytics Service Working";
    }
}