package com.example.productservice.controller;

import com.example.productservice.dto.*;
import com.example.productservice.dto.api.APIResponse;
import com.example.productservice.dto.api.APIResponseFailure;
import com.example.productservice.dto.api.APIResponseSuccess;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;

    // https://stackoverflow.com/a/19232501/6818945
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public HttpEntity<APIResponse> getProductById(@PathVariable("productId") Long productId) throws Exception {
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("class-name", "integrating APIS");
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Product product = productService.getProductById(productId);

            if(Objects.isNull(product)){
                response = new APIResponseSuccess<ProductResponseDTO>(null);
                // spring will strip the body for 204 status code
                // change status code to something else to see some content in body (response: null)
                httpStatus = HttpStatus.NO_CONTENT;
            }
            else{
                response = new APIResponseSuccess<ProductResponseDTO>(ProductResponseDTO.fromProduct(product));
                httpStatus = HttpStatus.OK;
            }

//            return new ResponseEntity<>(ProductResponseDTO.fromProduct(product),headers, HttpStatus.OK);
        } catch (Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, headers, httpStatus);
    }

    @GetMapping("/")
    public HttpEntity<APIResponse> getAllProducts(){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            List<Product> products = productService.getAllProducts();
            response = new APIResponseSuccess<List<ProductResponseDTO>>(ProductResponseDTO.fromProductList(products));
            httpStatus = HttpStatus.OK;
        }
        catch(Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }

    @PostMapping("/")
    public HttpEntity<APIResponse> createProduct(@RequestBody AddProductRequestDTO dto){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.CREATED;

        try{
            Product product = productService.addProduct(Product.fromAddProductRequestDTO(dto));
            response = new APIResponseSuccess<>(AddProductResponseDTO.fromProduct(product));
            httpStatus = HttpStatus.CREATED;
        }
        catch(Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<APIResponse> editProduct(@PathVariable(name = "productId") Long productId, @RequestBody EditProductRequestDTO dto) throws ProductNotFoundException {
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Product product = productService.editProduct(productId, Product.fromEditProductRequestDTO(dto));
            response = new APIResponseSuccess<>(EditProductResponseDTO.fromProduct(product));
            httpStatus = HttpStatus.OK;
        }
        catch(ProductNotFoundException e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

//            // https://stackoverflow.com/a/32445360/6818945
//            return ResponseEntity
//                    .status(HttpStatus.OK)
////                    .status(HttpStatus.NO_CONTENT)
//                    // if the status is 204, spring will strip the body completely,
//                    // so below line will work only for non 204 status codes
//                    .body("something went wrong");
////            return new ResponseEntity<>("something wrong",HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(path = "/{productId}")
    public HttpEntity<APIResponse> deleteProduct(@PathVariable(name = "productId") Long productId){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            Product deletedProduct = productService.deleteProduct(productId);
            response = new APIResponseSuccess<>(ProductResponseDTO.fromProduct(deletedProduct));
            // no need to set httpStatus as it was set to happy scenario while declaration above
//            httpStatus = HttpStatus.OK;
        }
        catch (ProductNotFoundException e) {
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        catch (Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }


    @GetMapping("/{userId}/{productId}")
    public HttpEntity<APIResponse> getProductById1(@PathVariable Long userId, @PathVariable Long productId) throws Exception {
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            ProductUserDTO productUserDTO = productService.getProductById1(userId,productId);

            if(Objects.isNull(productUserDTO)){
                response = new APIResponseSuccess<ProductUserDTO>(null);
                // spring will strip the body for 204 status code
                // change status code to something else to see some content in body (response: null)
                httpStatus = HttpStatus.NO_CONTENT;
            }
            else{
                response = new APIResponseSuccess<ProductUserDTO>(productUserDTO);
                httpStatus = HttpStatus.OK;
            }

//            return new ResponseEntity<>(ProductResponseDTO.fromProduct(product),headers, HttpStatus.OK);
        } catch (Exception e){
            response = new APIResponseFailure(e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}

/**
 * HW
 * 1. Go through http status codes
 * 2. Try to take input as headers
 * 3. Implement Update and PUT functionalities **
 * 4. Think of a way which helps us to return some response back to client
 *      in case of errors.
 */