package com.ridelog.analyticsservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI rideLogOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RideLog Analytics API")
                        .version("1.0")
                        .description("""
                            RideLog is a motorcycle ride tracking platform.

                            Features:
                            • User Authentication
                            • Bike Management
                            • Ride Tracking
                            • Analytics Dashboard

                            Built using:
                            Spring Boot
                            Spring Security
                            PostgreSQL
                            Eureka Discovery
                            API Gateway
                            Swagger/OpenAPI
                            """));
    }
}
