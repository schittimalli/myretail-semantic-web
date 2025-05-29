package com.myretail.mapper;


import com.myretail.model.Product;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.model.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class ProductMapperTest {

    private ProductMapper productMapper;
    private BindingSet mockBindingSet;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
        mockBindingSet = mock(BindingSet.class);
    }

    @Test
    void testToProduct_AllFieldsPresent() {
        // Mocking a scenario with all bindings available
        when(mockBindingSet.hasBinding("id")).thenReturn(true);
        when(mockBindingSet.hasBinding("name")).thenReturn(true);
        when(mockBindingSet.hasBinding("brand")).thenReturn(true);
        when(mockBindingSet.hasBinding("category")).thenReturn(true);
        when(mockBindingSet.hasBinding("imageURL")).thenReturn(true);

        Value mockedId = SimpleValueFactory.getInstance().createLiteral("12345");
        Value mockedName = SimpleValueFactory.getInstance().createLiteral("Sample Product");
        Value mockedBrand = SimpleValueFactory.getInstance().createLiteral("Sample Brand");
        Value mockedCategory = SimpleValueFactory.getInstance().createLiteral("Electronics");
        Value mockedImageURL = SimpleValueFactory.getInstance().createLiteral("http://example.com/image.jpg");

        when(mockBindingSet.getValue("id")).thenReturn(mockedId);
        when(mockBindingSet.getValue("name")).thenReturn(mockedName);
        when(mockBindingSet.getValue("brand")).thenReturn(mockedBrand);
        when(mockBindingSet.getValue("category")).thenReturn(mockedCategory);
        when(mockBindingSet.getValue("imageURL")).thenReturn(mockedImageURL);

        Product product = productMapper.toProduct(mockBindingSet);

        assertNotNull(product);
        assertEquals("12345", product.getId());
        assertEquals("Sample Product", product.getName());
        assertEquals("Sample Brand", product.getBrand());
        assertEquals("Electronics", product.getCategory());
        assertNotNull(product.getImage());
        assertEquals("http://example.com/image.jpg", product.getImage().getUrl());
    }

    @Test
    void testToProduct_MissingOptionalFields() {
        // Mocking a scenario where optional fields are missing but required fields exist
        when(mockBindingSet.hasBinding("id")).thenReturn(true);
        when(mockBindingSet.hasBinding("name")).thenReturn(true);
        when(mockBindingSet.hasBinding("brand")).thenReturn(false);
        when(mockBindingSet.hasBinding("category")).thenReturn(false);
        when(mockBindingSet.hasBinding("imageURL")).thenReturn(false);

        Value mockedId = SimpleValueFactory.getInstance().createLiteral("67890");
        Value mockedName = SimpleValueFactory.getInstance().createLiteral("Another Product");

        when(mockBindingSet.getValue("id")).thenReturn(mockedId);
        when(mockBindingSet.getValue("name")).thenReturn(mockedName);

        Product product = productMapper.toProduct(mockBindingSet);

        assertNotNull(product);
        assertEquals("67890", product.getId());
        assertEquals("Another Product", product.getName());
        assertNull(product.getBrand());
        assertNull(product.getCategory());
        assertNull(product.getImage());
    }

    @Test
    void testToProduct_NoFieldsPresent() {
        // Mocking a scenario where no fields are present
        when(mockBindingSet.hasBinding(anyString())).thenReturn(false);

        Product product = productMapper.toProduct(mockBindingSet);

        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getBrand());
        assertNull(product.getCategory());
        assertNull(product.getImage());
    }

    @Test
    void testToProduct_InvalidBindingSet() {
        // Mock a scenario with invalid data in the BindingSet
        when(mockBindingSet.hasBinding("id")).thenReturn(true);
        when(mockBindingSet.getValue("id")).thenThrow(new IllegalArgumentException("Invalid binding value"));

        assertThrows(IllegalArgumentException.class, () -> productMapper.toProduct(mockBindingSet));
    }
}