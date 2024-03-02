package com.example.productservice_wfs;

import com.example.productservice_wfs.models.Category;
import com.example.productservice_wfs.models.Product;
import com.example.productservice_wfs.repositories.CategoryRepository;
import com.example.productservice_wfs.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void saveProductAndCategory(){
        Category c = new Category();
        c.setName("Electronics");
        c.setDescription("electronic category desc");
        Category savedC = categoryRepository.save(c);

        Product p = new Product();
        p.setTitle("iPhone 15");
        p.setCategory(savedC);
        Product savedP = productRepository.save(p);

//        System.out.println(savedC);
        System.out.println(savedP.getTitle());
        System.out.println(savedC.getName());
    // contructor injection for some reason is not working thus using @Autowired above props
//    public ProductRepoTest(ProductRepository productRepository, CategoryRepository categoryRepository) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }

    @Test
    void AddProductTest(){
        Category c = new Category();
        c.setName("electronics");
        c.setDescription("category description");
        Category savedCategory = categoryRepository.save(c);

        Product p = new Product();
        p.setTitle("iphone");
        p.setDescription("apple phone");
        p.setCategory(savedCategory);

        Product savedProduct = productRepository.save(p);

        Optional<Product> retrievedProductOptional = productRepository.findById(savedProduct.getId());
        if(retrievedProductOptional.isEmpty()) return;
        Product retrievedProduct = retrievedProductOptional.get();

//        // trying to do ".toString()" will cause lombok to constantly go through product.category.products.category and thus will receive StackOverflow exception
//        // however, if we remove the List<Product> products property from Category model - it will break the infinite loop & toString() will work as expected
//        System.out.println(retrievedProduct.getCategory().toString());
//        System.out.println(retrievedProduct.toString());

        System.out.println(retrievedProduct.getTitle());
        // for FetchType.LAZY, this line will trigger a new query to fetch category from DB -- this can be seen in logs of this test case
        System.out.println(retrievedProduct.getCategory().getName());


    }
}
