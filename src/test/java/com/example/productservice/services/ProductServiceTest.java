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

    @Test
    @DisplayName("TEMP -- insert dummy data")
    public void TEMP_InsertDummyData() {
        // Arrange
        Product p1 = new Product();
        p1.setTitle("laptop");

        // Act
        for(int i = 0; i < 10; i++){
            productRepository.save(p1);
            p1.setId(p1.getId()+1);
        }

        p1.setId(p1.getId()+1);
        p1.setTitle("Laptop 1");
        productRepository.save(p1);
        System.out.print(p1.toString());
    }
}
