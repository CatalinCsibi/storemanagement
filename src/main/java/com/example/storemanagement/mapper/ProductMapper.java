package com.example.storemanagement.mapper;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);
}
