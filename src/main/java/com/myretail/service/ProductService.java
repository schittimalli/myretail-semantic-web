package com.myretail.service;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Product;
import com.myretail.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final String ENDPOINT = "http://localhost:3030/myretail/sparql";
    private static final String NS = "http://bestbuy.com/ontology#";

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    }
}
