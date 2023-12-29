package com.example.storemanagement.controller;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    @RequestMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product entity) {
        var productDto = productService.addProduct(entity);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }
}
