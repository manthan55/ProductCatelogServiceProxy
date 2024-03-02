package com.example.productservice_wfs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category extends BaseModel {
////    specify max length of a column (max Varchar length)
//    @Column(length = 10)
    String name;
    String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    List<Product> products;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    public static Category fromString(String category){
        Category c = new Category();
        c.setName(category);
        c.setDescription(category);
        return c;
    }
    
    public static List<Category> fromStringList(List<String> categories){
        List<Category> categoriesList = new ArrayList<>();

        for (String category :
                categories) {
            categoriesList.add(fromString(category));
        }
        
        return categoriesList;
    }
}
