package com.example.productservice.fakestoreapi.models;

import com.example.productservice.models.Product;
import lombok.Data;

@Data
public class FSProduct {
    String id;
    String title;
    Double price;
    String description;
    String category;
    String image;
    FSRating rating;

    public static FSProduct fromProduct(Product product){
        FSProduct fsProduct = new FSProduct();
        fsProduct.setTitle(product.getTitle());
        fsProduct.setPrice(product.getPrice());
        fsProduct.setDescription(product.getDescription());
        fsProduct.setCategory(product.getCategory().getName());
        fsProduct.setImage(product.getImage());
        fsProduct.setRating(new FSRating(product.getRating().getRate(), product.getRating().getCount()));
        return fsProduct;
    }
}
