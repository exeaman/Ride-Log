package com.ridelog.analyticsservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeResponse {
    private Long id;
    private String brand;
    private String model;
    private boolean active;
}
