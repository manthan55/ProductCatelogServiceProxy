package com.example.productservice.service;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchProducts(String query){
//        return productRepository.findByTitleEquals(query);

        // https://www.baeldung.com/spring-jpa-like-queries
        return productRepository.findAllByTitleLike("%"+query+"%");
    }

    public Page<Product> searchProducts(String query, int pageNo, int pageSize){
//        return productRepository.findAllByTitleLike(query);
        return productRepository.findByTitleEquals(query, PageRequest.of(pageNo, pageSize));
    }
}
