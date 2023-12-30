package com.example.storemanagement.controller;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product entity) {
        var productDto = productService.addProduct(entity);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        var updatedProduct = productService.updateProduct(productDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("name") String name) {
        productService.deleteProduct(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
