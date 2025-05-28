package com.myretail.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductGraphQLIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void testGetProductById() {
        graphQlTester
            .documentName("product-by-id") // Refers to resources/graphql-test/product-by-id.graphql
            .variable("id", "BBY-0003")
            .execute()
            .path("product.id").entity(String.class).isEqualTo("BBY-0003")
            .path("product.name").entity(String.class).satisfies(name -> {
                System.out.println("Product name: " + name);
            });
    }
}
