package com.example.productservice_wfs.dto;

import lombok.Data;

@Data
public class EditProductRequestDTO {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
