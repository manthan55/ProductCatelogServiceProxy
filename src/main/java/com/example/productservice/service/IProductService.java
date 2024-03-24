package com.example.productservice.service;

import com.example.productservice.dto.ProductUserDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product editProduct(Long productId, Product product) throws ProductNotFoundException;

    Product deleteProduct(Long productId) throws ProductNotFoundException;

    ProductUserDTO getProductById1(Long userId, Long productId);
}
