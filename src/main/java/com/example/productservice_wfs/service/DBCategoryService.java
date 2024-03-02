package com.example.productservice_wfs.service;

import com.example.productservice_wfs.models.Category;
import com.example.productservice_wfs.repositories.CategoryRepository;
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
