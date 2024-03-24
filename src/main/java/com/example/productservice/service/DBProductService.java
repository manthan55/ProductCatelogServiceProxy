package com.example.productservice.service;

import com.example.productservice.dto.ProductUserDTO;
import com.example.productservice.dto.UserDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

// https://stackoverflow.com/a/19232501/6818945
@Service("DBProductService")
public class DBProductService implements IProductService{
    private ProductRepository productRepository;
    private RestTemplate restTemplate;

    public DBProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId);
        if(optProduct.isEmpty()) return null;
        return optProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Product addedProduct = productRepository.save(product);
        return addedProduct;
    }

    @Override
    public Product editProduct(Long productId, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        // first check if product exists
        Product existingProduct = getProductById(productId);
        if(existingProduct == null) throw new ProductNotFoundException("Product : "+productId+" not found to delete");

        productRepository.deleteById(productId);
        return existingProduct;
    }

    @Override
    public ProductUserDTO getProductById1(Long userId, Long productId){
        Optional<Product> optProduct = productRepository.findById(productId);
        if(optProduct.isEmpty()) return null;

        System.out.println("http://UserService/users/");

        UserDTO userDTO = restTemplate.getForEntity("http://UserService/users/{id}",UserDTO.class,userId).getBody();
        System.out.println(userDTO);

        return ProductUserDTO.fromProductUser(optProduct.get(), userDTO);
    }
}
