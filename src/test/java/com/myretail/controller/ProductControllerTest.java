package com.myretail.controller;

import com.myretail.model.Product;
import com.myretail.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {
    private ProductController productController;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void testProducts() {
        // Mocked data
        Product product1 = new Product();

        product1.setId("BBY-001");
        product1.setName("Phone");
        product1.setBrand("BrandX");
        product1.setCategory("Electronics");

        Product product2 = new Product();
        product2.setId("BBY-002");
        product2.setName("Laptop");
        product2.setBrand("BrandY");
        product2.setCategory("Computing");

        List<Product> mockProducts = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Call the controller method
        List<Product> products = productController.products();

        // Verify results
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("BBY-001", products.get(0).getId());
    }

    @Test
    void testProduct() {
        // Mocked data
        Product product = new Product();
        product.setId("BBY-003");
        product.setName("TV");
        product.setBrand("BrandZ");
        product.setCategory("BrandZ");
        // Mock behavior
        when(productService.getProductById("BBY-003")).thenReturn(product);

        // Call the controller method
        Product result = productController.product("BBY-003");

        // Verify results
        assertNotNull(result);
        assertEquals("BBY-003", result.getId());
        assertEquals("TV", result.getName());
    }
}