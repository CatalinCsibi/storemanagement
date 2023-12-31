package com.example.storemanagement.service;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.exception.ProductNotRegisteredException;
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

import java.util.List;
import java.util.Optional;

import static com.example.storemanagement.utils.ProductTestUtils.*;
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

    @Test
    public void testFindAllProducts() {
        when(productRepository.findAll()).thenReturn(getListOfProducts());

        List<ProductDto> productDtos = productService.findAllProducts();

        assertEquals(getListOfProductDtos(), productDtos);
    }


    @Test
    public void testUpdateProductWithSuccess() {
        var savedProduct = getSecondProduct();
        savedProduct.setName("product");

        when(productRepository.findByName(any())).thenReturn(Optional.ofNullable(getProduct()));
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        var expectedProductDto = getSecondProductDto();
        expectedProductDto.setName("product");

        var actualProductDto = productService.updateProduct(expectedProductDto);

        verify(productRepository, times(1)).findByName(any());
        verify(productRepository, times(1)).save(any());
        assertEquals(expectedProductDto, actualProductDto);
    }

    @Test
    public void testUpdateProductFailureWhenProductWasNotFound() {
        when(productRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(ProductNotRegisteredException.class, () -> {
            productService.updateProduct(getProductDto());
        });

        verify(productRepository, times(1)).findByName(any());
    }

    @Test
    public void testDeleteProductWithSuccess() {
        when(productRepository.findByName(any())).thenReturn(Optional.ofNullable(getProduct()));

        productService.deleteProduct("product");

        verify(productRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteProductFailureWhenProductWasNotFound() {
        when(productRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(ProductNotRegisteredException.class, () -> {
            productService.deleteProduct(anyString());
        });

        verify(productRepository, times(1)).findByName(any());
        verify(productRepository, times(0)).delete(any());

    }
}
