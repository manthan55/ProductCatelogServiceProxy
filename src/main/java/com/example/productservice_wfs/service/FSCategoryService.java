package com.example.productservice_wfs.service;

import com.example.productservice_wfs.fakestoreapi.FSClient;
import com.example.productservice_wfs.models.Category;
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
