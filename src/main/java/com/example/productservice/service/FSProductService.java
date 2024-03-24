package com.example.productservice.service;

import com.example.productservice.dto.ProductUserDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.fakestoreapi.FSClient;
import com.example.productservice.fakestoreapi.models.FSProduct;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

// https://stackoverflow.com/a/19232501/6818945
@Service("FSProductService")
//@Primary
public class FSProductService implements IProductService {

    private FSClient fsClient;

    public FSProductService(FSClient fsClient) {
        this.fsClient = fsClient;
    }

    @Override
    public Product getProductById(Long productId) {
        FSProduct fsProduct = fsClient.getProduct(productId);
        return Product.fromFSProduct(fsProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FSProduct> fsProducts = fsClient.getAllProducts();
        return Product.fromFSProductList(fsProducts);
    }

    @Override
    public Product addProduct(Product product) {
        FSProduct fsProduct = fsClient.addProduct(FSProduct.fromProduct(product));
        return Product.fromFSProduct(fsProduct);

//
//        FakeStoreCreateProductRequest product = new FakeStoreCreateProductRequest();
//        product.setTitle(title);
//        product.setPrice(price);
//        product.setDescription(description);
//        product.setImage(image);
//        product.setCategory(category);
//
//        HttpEntity<String> body = new HttpEntity<String>(product.toString());
//
//        FakeStoreProductResponse dto = restTemplate
//                .build()
//                .postForEntity(
//                        "https://fakestoreapi.com/products",
//                        product,
//                        FakeStoreProductResponse.class
//                )
//                .getBody();
////                .postForObject(
////                        "https://fakestoreapi.com/products",
////                        product,
////                        FakeStoreProductResponse.class
////                );
//
//        return dto;
    }

    @Override
    public Product editProduct(Long productId, Product product) throws ProductNotFoundException {
        // first check if product exists
        Product existingProduct = getProductById(productId);
        if(existingProduct == null) throw new ProductNotFoundException("Product : "+productId+" not found to edit");

        // ToDo -- in JPA -- check what happens if we dont specify rating like follows -- does it reset in DB or use existing
        product.setRating(existingProduct.getRating());

        FSProduct updatedProduct = fsClient.updateProduct(productId,FSProduct.fromProduct(product));
        return Product.fromFSProduct(updatedProduct);
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        // first check if product exists
        Product existingProduct = getProductById(productId);
        if(existingProduct == null) throw new ProductNotFoundException("Product : "+productId+" not found to delete");

        FSProduct deletedProduct = fsClient.deleteProduct(productId);
        return Product.fromFSProduct(deletedProduct);
    }

    @Override
    public ProductUserDTO getProductById1(Long userId, Long productId) {
        return null;
    }
}
