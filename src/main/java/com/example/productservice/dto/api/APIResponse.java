package com.example.productservice.dto.api;

import lombok.Data;

@Data
public class APIResponse {
    private String rayId;

    public APIResponse() {
        this.rayId = Long.valueOf(System.currentTimeMillis()).toString();
    }
}
