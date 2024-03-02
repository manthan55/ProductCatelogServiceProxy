package com.example.productservice_wfs.dto;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.example.productservice_wfs.models.Category;
import com.example.productservice_wfs.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    String name;
    String description;

    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public static CategoryResponseDTO fromCategory(Category category){
        if(category == null) return null;

        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }

    public static List<CategoryResponseDTO> fromCategoryList(List<Category> categories){
        List<CategoryResponseDTO> dtos = new ArrayList<>();

        for (Category category :
                categories) {
            dtos.add(CategoryResponseDTO.fromCategory(category));
        }

        return dtos;
    }
}
