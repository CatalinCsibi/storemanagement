package com.example.storemanagement.controller;

import com.example.storemanagement.dto.ProductDto;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.exception.ProductNotRegisteredException;
import com.example.storemanagement.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.storemanagement.utils.ProductTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerUnitTest {


    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;


    @Test
    public void testAddProductWithSuccess() {
        var productDto = getProductDto();

        when(productService.addProduct(any())).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.addProduct(getProduct());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDto, response.getBody());
        verify(productService, times(1)).addProduct(any());
    }

    @Test
    public void testAddProductFailureWhenProductIsAlreadyRegistered() {
        when(productService.addProduct(any())).thenThrow(ProductAlreadyRegisteredException.class);

        assertThrows(ProductAlreadyRegisteredException.class, () -> productController.addProduct(any()));
        verify(productService, times(1)).addProduct(any());
    }

    @Test
    public void testFindAllProducts() {
        List<ProductDto> productDtos = getListOfProductDtos();

        when(productService.findAllProducts()).thenReturn(productDtos);

        ResponseEntity<List<ProductDto>> response = productController.findAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDtos, response.getBody());
        verify(productService, times(1)).findAllProducts();
    }

    @Test
    public void testUpdateProductWithSuccess() {
        var productDto = getProductDto();

        when(productService.updateProduct(productDto)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.updateProduct(getProductDto());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    public void testUpdateProductFailureWhenProductWasNotFound() {
        when(productService.updateProduct(any())).thenThrow(ProductNotRegisteredException.class);

        assertThrows(ProductNotRegisteredException.class, () -> productController.updateProduct(any()));
        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    public void deleteProductWithSuccess() {
        doNothing().when(productService).deleteProduct(any());

        ResponseEntity<Void> response = productController.deleteProduct(any());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(any());
    }

    @Test
    public void deleteProductFailureWhenProductCouldNotBeFound() {
        doThrow(new ProductNotRegisteredException("", "")).when(productService).deleteProduct(anyString());

        assertThrows(ProductNotRegisteredException.class, () -> productController.deleteProduct(anyString()));
        verify(productService, times(1)).deleteProduct(anyString());
    }

}
