package com.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HealthCheckResponseDTO {
    private String version;
    private Date timestamp;
}
