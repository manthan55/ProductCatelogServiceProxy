package com.example.productservice_wfs.fakestoreapi;

import lombok.Data;

@Data
public class FakeStoreCreateProductRequest {
    String title;
    Double price;
    String description;
    String image;
    String category;
}
