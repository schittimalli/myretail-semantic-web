package com.myretail.service;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.exception.SPARQLQueryException;
import com.myretail.model.Product;
import com.myretail.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;



    @Test
    void testGetAllProducts() {
        // Given mock data
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

        when(productRepository.findAll()).thenReturn(mockProducts);

        // Then test the results
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("BBY-001", products.get(0).getId());
        assertEquals("Phone", products.get(0).getName());
    }

    @Test
    void testGetProductById() {
        // Given mock data
        Product product = new Product();
        product.setId("BBY-003");
        product.setName("TV");
        product.setBrand("BrandZ");
        product.setCategory("BrandZ");

        when(productRepository.findById("BBY-003")).thenReturn(Optional.of(product));

        // When service is called
        Product result = productService.getProductById("BBY-003");

        // Then validate results
        assertNotNull(result);
        assertEquals("BBY-003", result.getId());
        assertEquals("TV", result.getName());
    }

    @Test
    void testGetAllProducts_EmptyList() {

        when(productRepository.findAll()).thenThrow(new SPARQLQueryException("DB is down"));
        assertThrows(SPARQLQueryException.class, () -> {
                    List<Product> products = productService.getAllProducts();
                });
    }

    @Test
    void testGetAllProducts_SPARQLQueryException() {

        when(productRepository.findById("BBY-0003")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById("BBY-0003");
        });


    }

}