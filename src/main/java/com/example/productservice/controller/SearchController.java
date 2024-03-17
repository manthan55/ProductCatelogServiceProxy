package com.example.productservice.controller;

import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.dto.SearchRequestDTO;
import com.example.productservice.dto.api.APIResponse;
import com.example.productservice.dto.api.APIResponseSuccess;
import com.example.productservice.models.Product;
import com.example.productservice.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("")
    public ResponseEntity<APIResponse> searchProducts(@RequestBody SearchRequestDTO requestDTO){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            List<Product> products = searchService.searchProducts(requestDTO.getQuery());
//            Page<Product> products = searchService.searchProducts(requestDTO.getQuery(), requestDTO.getPageNo(), requestDTO.getPageSize());
            response = new APIResponseSuccess<List<ProductResponseDTO>>(ProductResponseDTO.fromProductList(products));
            httpStatus = HttpStatus.OK;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            response = null;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }
}
