package com.example.productservice_wfs.fakestoreapi;

import com.example.productservice_wfs.fakestoreapi.models.FSProduct;

import java.util.List;

public interface IFakeStoreAdapter {
    FSProduct getProduct(Long id);
    List<FSProduct> getAllProducts();
    FSProduct addProduct(FSProduct product);
    FSProduct updateProduct(Long id, FSProduct product);
    FSProduct deleteProduct(Long id);
    List<String> getAllCategories();
}
