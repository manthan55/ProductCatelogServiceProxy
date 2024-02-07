package org.example.productcatalogserviceproxy.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
