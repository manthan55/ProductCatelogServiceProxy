package com.example.productservice.models;


import com.example.productservice.dto.AddProductRequestDTO;
import com.example.productservice.dto.EditProductRequestDTO;
import com.example.productservice.fakestoreapi.models.FSProduct;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
// to provide custom table name
//@Entity(name = "prds")
@Entity
public class Product extends BaseModel{
    String title;
    Double price;
    String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Category category;
    String image;
    @OneToOne(cascade = CascadeType.ALL)
    Rating rating;

    public static Product fromFSProduct(FSProduct fsProduct){
        if(fsProduct == null) return null;
        
        Product p = new Product();
        p.setId(Long.parseLong(fsProduct.getId()));
        p.setTitle(fsProduct.getTitle());
        p.setPrice(fsProduct.getPrice());
        p.setDescription(fsProduct.getDescription());
        p.setImage(fsProduct.getImage());

        Category c = new Category();
        c.setName(fsProduct.getCategory());
        c.setDescription(fsProduct.getCategory());
        p.setCategory(c);

        // FakeStore does not return rating on addition/updation of a new product
        // but it does for GetSingleProduct & GetAllProducts
        if(fsProduct.getRating() != null) p.setRating(new Rating(fsProduct.getRating()));

        return p;
    }


    public static List<Product> fromFSProductList(List<FSProduct> fsProducts){
        List<Product> products = new ArrayList<>();

        for (FSProduct fsProduct : fsProducts) {
            products.add(fromFSProduct(fsProduct));
        }

        return products;
    }

    public static Product fromAddProductRequestDTO(AddProductRequestDTO productDTO){
        if(productDTO == null) return null;
        
        Product p = new Product();
        p.setTitle(productDTO.getTitle());
        p.setPrice(productDTO.getPrice());
        p.setDescription(productDTO.getDescription());
        p.setImage(productDTO.getImage());

        Category c = new Category();
        c.setName(productDTO.getCategory());
        c.setDescription(productDTO.getCategory());
        p.setCategory(c);

        // new product has no rating
        // can use lombok all args generated constructor instead of static from__ methods (like we did above for category)
        p.setRating(new Rating(0.0,0));

        return p;
    }

    public static Product fromEditProductRequestDTO(EditProductRequestDTO productDTO){
        if(productDTO == null) return null;

        Product p = new Product();
        p.setTitle(productDTO.getTitle());
        p.setPrice(productDTO.getPrice());
        p.setDescription(productDTO.getDescription());
        p.setImage(productDTO.getImage());

        Category c = new Category();
        c.setName(productDTO.getCategory());
        c.setDescription(productDTO.getCategory());
        p.setCategory(c);

        // for editing a product, callers don't pass rating (should not)
        // we must take the exiting rating of the product
        // the setting of existing rating of this product is done in service layer

        return p;
    }
}
