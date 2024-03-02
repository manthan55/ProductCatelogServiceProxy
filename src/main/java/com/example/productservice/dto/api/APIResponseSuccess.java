package com.example.productservice.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponseSuccess<T> extends APIResponse {
    private T response;
}
