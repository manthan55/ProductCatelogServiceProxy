package com.example.productservice_wfs.controller;

import com.example.productservice_wfs.dto.CategoryResponseDTO;
import com.example.productservice_wfs.dto.api.APIResponse;
import com.example.productservice_wfs.dto.api.APIResponseFailure;
import com.example.productservice_wfs.dto.api.APIResponseSuccess;
import com.example.productservice_wfs.service.ICategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private ICategoryService categoryService;

    public CategoryController(@Qualifier("DBCategoryService") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    private HttpEntity<APIResponse> getAllCategories(){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            response = new APIResponseSuccess<>(CategoryResponseDTO.fromCategoryList(categoryService.getAllCategories()));
        }
        catch(Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/headers")
    private String readHeader(@RequestHeader(name = "my-custom-header", required = false) String headerValue){
        return "Header value : "+headerValue;
    }
}
