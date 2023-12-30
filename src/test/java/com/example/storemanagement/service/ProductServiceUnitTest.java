package com.example.storemanagement.service;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.mapper.ProductMapper;
import com.example.storemanagement.mapper.ProductMapperImpl;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapperImpl();


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(productService, "productMapper", productMapper);
    }

    @Test
    public void testAddProductWithSuccess() {

        var expectedProduct = getProduct();

        when(productRepository.findByName(any())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        var actualProduct = productService.addProduct(expectedProduct);

        verify(productRepository, times(1)).findByName(any());
        verify(productRepository, times(1)).save(any());

        assertEquals(getProductDto(), actualProduct);
    }

    @Test
    public void testAddProductFailureWhenProductIsAlreadyRegistered() {
        var product = getProduct();
        when(productRepository.findByName(any())).thenReturn(Optional.ofNullable(product));

        assertThrows(ProductAlreadyRegisteredException.class, () -> {
            assert product != null;
            productService.addProduct(product);
        });

        verify(productRepository, times(1)).findByName(any());
    }


    private Product getProduct() {
        return Product.builder()
                .id(23)
                .name("product")
                .description("used for something")
                .price(123.45)
                .build();
    }

    private ProductDto getProductDto() {
        return ProductDto.builder()
                .name("product")
                .description("used for something")
                .price(123.45)
                .build();
    }
}
