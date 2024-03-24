package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {


    @Autowired
    private ProductRepository productRepository;

//    @Test
//    @DisplayName("TEMP -- insert dummy data")
    public void TEMP_InsertDummyData() {
        // Arrange
        Product p1 = new Product();
        p1.setTitle("laptop");

        Product p2 = new Product();
        p2.setTitle("laptop");

        Product p3 = new Product();
        p3.setTitle("laptop");

        Product p4 = new Product();
        p4.setTitle("laptop");

        Product p5 = new Product();
        p5.setTitle("laptop");

        Product p6 = new Product();
        p6.setTitle("laptop");

        // Act
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
    }
}
