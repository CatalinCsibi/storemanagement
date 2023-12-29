package com.example.storemanagement.service;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.mapper.ProductMapper;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto addProduct(Product product) {
        log.info("Adding product: " + product.getName());
        productRepository.save(product);
        return productMapper.productToProductDto(product);
    }


}
