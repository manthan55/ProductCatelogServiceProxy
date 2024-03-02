package com.example.productservice_wfs.service;

import com.example.productservice_wfs.exceptions.ProductNotFoundException;
import com.example.productservice_wfs.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product editProduct(Long productId, Product product) throws ProductNotFoundException;

    Product deleteProduct(Long productId) throws ProductNotFoundException;
}
