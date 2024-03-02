package com.example.productservice.fakestoreapi;

import lombok.Data;

@Data
public class FakeStoreCreateProductRequest {
    String title;
    Double price;
    String description;
    String image;
    String category;
}
