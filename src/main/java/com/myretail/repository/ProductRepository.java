package com.myretail.repository;

import com.myretail.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(String id);
}