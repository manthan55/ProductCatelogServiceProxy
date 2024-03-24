package com.example.productservice.dto;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUserDTO {
    private ProductResponseDTO product;
    private UserDTO user;


    public static ProductUserDTO fromProductUser(Product product, UserDTO user){
        if(product == null) return null;

        ProductUserDTO dto = new ProductUserDTO();
        dto.setProduct(ProductResponseDTO.fromProduct(product));
        dto.setUser(user);

        return dto;
    }
}
