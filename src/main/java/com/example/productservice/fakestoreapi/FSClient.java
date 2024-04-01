package com.example.productservice.fakestoreapi;

import com.example.productservice.fakestoreapi.models.FSProduct;
import com.example.productservice.utils.HttpUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FSClient implements IFakeStoreAdapter{
    private RestTemplate restTemplate;
    private HttpUtils httpUtils;
//     private String fakestoreapiHost = "https://fakestoreapi.com";
// using http:// as docker container is facing certificate issue while talking to https:// endpoint
    private String fakestoreapiHost = "http://fakestoreapi.com";

    public FSClient(RestTemplateBuilder restTemplateBuilder, HttpUtils httpUtils) {
        this.restTemplate = restTemplateBuilder.build();
//        this.restTemplate = restTemplateBuilder.requestFactory(new HttpComponentsClientHttpRequestFactory()).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        this.restTemplate.setRequestFactory(requestFactory)
        this.httpUtils = httpUtils;
    }


    @Override
    public FSProduct getProduct(Long id) {
        FSProduct product = restTemplate.getForEntity(
                fakestoreapiHost+"/products/{productId}",
                FSProduct.class,
                id
        ).getBody();
        return product;
    }

    @Override
    public List<FSProduct> getAllProducts() {
        FSProduct[] products = restTemplate.getForEntity(
                fakestoreapiHost+"/products",
                FSProduct[].class
        ).getBody();
        return Arrays.asList(products);
    }

    @Override
    public FSProduct addProduct(FSProduct product) {
        FSProduct addedProduct = restTemplate.postForEntity(
                fakestoreapiHost+"/products",
                product,
                FSProduct.class
        ).getBody();
        return addedProduct;
    }

    @Override
    public FSProduct updateProduct(Long id, FSProduct product) {
        // to make patch requests work in Spring3.RestTemplate
        // https://github.com/spring-projects/spring-boot/issues/33863
        // https://stackoverflow.com/a/76773258/6818945
        // https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#apache-httpclient-in-resttemplate

        // why does PATCH not work by default?
        // [ANS] The standard JDK HTTP library does not support HTTP PATCH. You need to use the Apache HttpComponents or OkHttp request factory
        // https://stackoverflow.com/questions/29447382/resttemplate-patch-request#:~:text=standard%20JDK%20HTTP%20library%20does%20not%20support%20HTTP%20PATCH
        FSProduct updatedProduct = restTemplate
                .patchForObject(
                        fakestoreapiHost+"/products/{productId}",
                        product,
                        FSProduct.class,
                        id
                );
        // put won't work as RestTemplate's put() does not return any object response
        return updatedProduct;
    }

    @Override
    public FSProduct deleteProduct(Long id) {
        FSProduct product = httpUtils
                .makeHttpCall(
                        HttpMethod.DELETE,
                        fakestoreapiHost+"/products/{productId}",
                        null,
                        FSProduct.class,
                        id
                ).getBody();

        // cannot use RestTemplate.Delete as it does not return any body
        return product;
    }

    @Override
    public List<String> getAllCategories() {
        String[] categories = restTemplate.getForEntity(
                fakestoreapiHost+"/products/categories",
                String[].class
        ).getBody();
        return Arrays.asList(categories);
    }
}
