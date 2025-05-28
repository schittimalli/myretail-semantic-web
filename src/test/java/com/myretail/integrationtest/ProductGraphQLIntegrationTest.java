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
            .path("product.name").entity(String.class).isEqualTo("LG OLED TV")
            .path("product.brand").entity(String.class).isEqualTo("http://bestbuy.com/ontology#LG")
            .path("product.category").entity(String.class).isEqualTo("http://bestbuy.com/ontology#TV")
            .path("product.image.url").entity(String.class).isEqualTo("https://s3.amazonaws.com/bestbuy/image3.jpg");
    }
}
