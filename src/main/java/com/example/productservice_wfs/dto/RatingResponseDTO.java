package com.example.productservice_wfs.dto;

import com.example.productservice_wfs.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingResponseDTO {
    private Long id;
    private Double rate;
    private Integer count;

    // it also has an all args constructor which accepts individual properties as we have added @AllArgsConstructor

    public RatingResponseDTO(Rating rating) {
        this.id = rating.getId();
        this.rate = rating.getRate();
        this.count = rating.getCount();
    }
}
