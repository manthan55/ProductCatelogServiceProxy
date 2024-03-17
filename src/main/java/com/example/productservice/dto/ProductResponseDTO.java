package com.example.productservice.dto;

import com.example.productservice.models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
// used by
// - GetProduct
// - GetAllProducts
// can also be substituted for AddProductResponseDTO & EditProductResponseDTO
public class ProductResponseDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private CategoryResponseDTO category;
    private RatingResponseDTO rating;

    public static ProductResponseDTO fromProduct(Product product){
        if(product == null) return null;

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());

        if(product.getCategory() != null) dto.setCategory(new CategoryResponseDTO(product.getCategory()));
        if(product.getRating() != null) dto.setRating(new RatingResponseDTO(product.getRating()));

        return dto;
    }

    public static List<ProductResponseDTO> fromProductList(List<Product> products){
        List<ProductResponseDTO> dtos = new ArrayList<>();

        if(products == null) return dtos;

        for (Product product :
                products) {
            dtos.add(ProductResponseDTO.fromProduct(product));
        }
        
        return dtos;
    }
}
