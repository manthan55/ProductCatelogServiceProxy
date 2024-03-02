package com.example.productservice.dto;

import com.example.productservice.models.Product;
import lombok.Data;

@Data
// can also reuse ProductResponseDTO instead of this class
public class EditProductResponseDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private CategoryResponseDTO category;

    public static EditProductResponseDTO fromProduct(Product product){
        if(product == null) return null;

        EditProductResponseDTO dto = new EditProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setCategory(new CategoryResponseDTO(product.getCategory()));

        return dto;
    }
}
