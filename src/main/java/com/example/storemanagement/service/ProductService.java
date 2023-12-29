package com.example.storemanagement.service;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.mapper.ProductMapper;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto addProduct(Product product) {
        log.info("Adding product: " + product.getName());

        productRepository.findByName(product.getName())
                .ifPresent(existingProduct -> {
                    throw new ProductAlreadyRegisteredException("Product with name %s is already registered", product.getName());
                });


        productRepository.save(product);
        return productMapper.productToProductDto(product);
    }

    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }


}
