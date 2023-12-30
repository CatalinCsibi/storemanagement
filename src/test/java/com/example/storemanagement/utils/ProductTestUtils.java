package com.example.storemanagement.utils;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.model.Product;

import java.util.List;

public final class ProductTestUtils {

    public static Product getProduct() {
        return Product.builder()
                .id(23)
                .name("product")
                .description("used for something")
                .price(123.45)
                .build();
    }

    public static Product getSecondProduct() {
        return Product.builder()
                .id(1)
                .name("product2")
                .description("used for something else")
                .price(543.21)
                .build();
    }

    public static ProductDto getProductDto() {
        return ProductDto.builder()
                .name("product")
                .description("used for something")
                .price(123.45)
                .build();
    }

    public static ProductDto getSecondProductDto() {
        return ProductDto.builder()
                .name("product2")
                .description("used for something else")
                .price(543.21)
                .build();
    }

    public static List<Product> getListOfProducts() {
        return List.of(getProduct(), getSecondProduct());
    }

    public static List<ProductDto> getListOfProductDtos() {
        return List.of(getProductDto(), getSecondProductDto());
    }
}
