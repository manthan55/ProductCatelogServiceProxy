package com.example.productservice.fakestoreapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FSRating implements Serializable {
    private Double rate;
    private Integer count;
}
