package com.example.productservice.service;

import com.example.productservice.models.Category;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DBCategoryService")
public class DBCategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    public DBCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
