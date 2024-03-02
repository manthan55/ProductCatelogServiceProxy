package com.example.productservice.fakestoreapi.models;

import com.example.productservice.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FSRating {
    private Double rate;
    private Integer count;

    public FSRating(Rating rating) {
        this.rate = rating.getRate();
        this.count = rating.getCount();
    }
}
