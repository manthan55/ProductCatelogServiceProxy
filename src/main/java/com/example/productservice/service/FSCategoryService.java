package com.example.productservice.service;

import com.example.productservice.fakestoreapi.FSClient;
import com.example.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FSCategoryService")
public class FSCategoryService implements ICategoryService {
    private FSClient fsClient;

    public FSCategoryService(FSClient fsClient) {
        this.fsClient = fsClient;
    }

    @Override
    public List<Category> getAllCategories() {
        List<String> response = fsClient.getAllCategories();
        return Category.fromStringList(response);
    }
}
