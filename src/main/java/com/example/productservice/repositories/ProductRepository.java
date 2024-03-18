package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleEquals(String title);

    // https://www.baeldung.com/spring-jpa-like-queries
    List<Product> findAllByTitleLike(String title);

//    List<Product> findByTitleEquals(String title, Pageable pageable);

    Page<Product> findByTitleEquals(String title, PageRequest pageRequest);
}
