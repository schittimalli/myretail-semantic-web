# **MyRetail Product Service**
A microservice that provides access to product details such as name, brand, category, and image details by querying a SPARQL endpoint.
## **Overview**
The `Product Service` is designed to query product data from a SPARQL-based triple store (RDF database). It integrates with RDF4J and SPARQL to retrieve information using a defined ontology.
The project uses **GraphQL** , **Spring Boot** and follows best practices for microservices development.
## **Features**
- Retrieve all products with detailed attributes such as:
    - **Product ID**
    - **Product Name**
    - **Brand**
    - **Category**
    - **Image URL**

- Fetch a single product by its ID.

- Integration with a SPARQL endpoint for querying product data.
- Exception handling for cases such as:
    - Product not found.
    - SPARQL query failure.

- Easy-to-extend architecture for new fields.

## **Technologies Used**
- **Java **: For backend development.
- **Spring Boot**: To create a robust, production-ready application.
- **RDF4J**: For semantic web (SPARQL) querying.
- **Maven**: For dependency and build management.
- **Mockito** and **JUnit**: For unit testing and mocking dependencies.

## **SPARQL Endpoint**
The microservice works with the following SPARQL endpoint:
- **Endpoint URL:** `http://localhost:3030/myretail/sparql`
- **Ontology Namespace:** `http://bestbuy.com/ontology#`

### SPARQL Queries
It retrieves data using pre-defined SPARQL queries.

## **How to Run**
### Prerequisites
- **Java 17** or later installed.
- **Apache Maven** installed.
- Running RDF4J SPARQL endpoint on Apache Jena Fuseki.

### Steps to Run
1. Clone the repository:
``` bash
    git clone https://github.com/your-repository/myretail-product-service.git
    cd myretail-product-service
```
1. Update the SPARQL endpoint in `application.properties` if needed:
``` properties
    sparql.endpoint=http://localhost:3030/myretail/sparql
```
1. Build the project using Maven:
``` bash
    mvn clean install
```
1. Start the Spring Boot application:
``` bash
    mvn spring-boot:run
```
1. Access the API on `http://localhost:8080`.

## **API Documentation**

1. **Get All Products**:
```

**Sample Request**
URL: http://localhost:8080/graphql
query {
  products {
    name
    brand
    image {
      url
    }
  }
}
**Sample Response**
{
  "data": {
    "products": [
      { "name": "Sony 4K Smart TV", "brand": "http://bestbuy.com/ontology#Sony", "image": { "url": "https://s3.amazonaws.com/bestbuy/image1.jpg" } },
      { "name": "Samsung OLED TV", "brand": "http://bestbuy.com/ontology#Samsung", "image": { "url": "https://s3.amazonaws.com/bestbuy/image2.jpg" } },
      { "name": "LG OLED TV", "brand": "http://bestbuy.com/ontology#LG", "image": { "url": "https://s3.amazonaws.com/bestbuy/image3.jpg" } },
      { "name": "Apple iPhone 14", "brand": "http://bestbuy.com/ontology#Apple", "image": { "url": "https://s3.amazonaws.com/bestbuy/image4.jpg" } },
      { "name": "Dell XPS 13", "brand": "http://bestbuy.com/ontology#Dell", "image": { "url": "https://s3.amazonaws.com/bestbuy/image5.jpg" } },
      { "name": "Sony Bluetooth Speaker", "brand": "http://bestbuy.com/ontology#Sony", "image": { "url": "https://s3.amazonaws.com/bestbuy/image6.jpg" } },
      { "name": "Samsung Soundbar", "brand": "http://bestbuy.com/ontology#Samsung", "image": { "url": "https://s3.amazonaws.com/bestbuy/image7.jpg" } },
      { "name": "LG Gram 16", "brand": "http://bestbuy.com/ontology#LG", "image": { "url": "https://s3.amazonaws.com/bestbuy/image8.jpg" } },
      { "name": "Apple MacBook Air M2", "brand": "http://bestbuy.com/ontology#Apple", "image": { "url": "https://s3.amazonaws.com/bestbuy/image9.jpg" } },
      { "name": "Dell Ultrasharp Monitor", "brand": "http://bestbuy.com/ontology#Dell", "image": { "url": "https://s3.amazonaws.com/bestbuy/image10.jpg" } },
      { "name": "Sony Xperia 5", "brand": "http://bestbuy.com/ontology#Sony", "image": { "url": "https://s3.amazonaws.com/bestbuy/image11.jpg" } },
      { "name": "Samsung Galaxy S22", "brand": "http://bestbuy.com/ontology#Samsung", "image": { "url": "https://s3.amazonaws.com/bestbuy/image12.jpg" } },
      { "name": "LG NanoCell TV", "brand": "http://bestbuy.com/ontology#LG", "image": { "url": "https://s3.amazonaws.com/bestbuy/image13.jpg" } },
      { "name": "Apple iPad Pro", "brand": "http://bestbuy.com/ontology#Apple", "image": { "url": "https://s3.amazonaws.com/bestbuy/image14.jpg" } },
      { "name": "Dell Latitude 7490", "brand": "http://bestbuy.com/ontology#Dell", "image": { "url": "https://s3.amazonaws.com/bestbuy/image15.jpg" } },
      { "name": "Sony WH-1000XM5", "brand": "http://bestbuy.com/ontology#Sony", "image": { "url": "https://s3.amazonaws.com/bestbuy/image16.jpg" } },
      { "name": "Samsung Galaxy Buds", "brand": "http://bestbuy.com/ontology#Samsung", "image": { "url": "https://s3.amazonaws.com/bestbuy/image17.jpg" } },
      { "name": "LG UltraWide Monitor", "brand": "http://bestbuy.com/ontology#LG", "image": { "url": "https://s3.amazonaws.com/bestbuy/image18.jpg" } },
      { "name": "Apple Watch Series 8", "brand": "http://bestbuy.com/ontology#Apple", "image": { "url": "https://s3.amazonaws.com/bestbuy/image19.jpg" } },
      { "name": "Dell Precision Workstation", "brand": "http://bestbuy.com/ontology#Dell", "image": { "url": "https://s3.amazonaws.com/bestbuy/image20.jpg" } }
    ]
  }
}

```
1. **Get Product by ID**:
```  

**Sample Request**
URL: http://localhost:8080/graphql
query {
product(id: "BBY-0003") {
id
name
brand
category
image {
url
}
}
}

**Sample Response**
{
"data": {
"product": {
"id": "BBY-0003",
"name": "LG OLED TV",
"brand": "http://bestbuy.com/ontology#LG",
"category": "http://bestbuy.com/ontology#TV",
"image": {
"url": "https://s3.amazonaws.com/bestbuy/image3.jpg"
}
}
}
}
```

```
## **Error Handling**

| Status Code | Description |
| --- | --- |
| `404` | Product not found. |
| `500` | SPARQL query execution failed. |

**GlobalGraphQLExceptionHandler**

This will handle API exceptions globaly
On error - Error message is included in response.

**Sample error Request**
GRAPHQL http://localhost:8080/graphql

query {
    product(id: "TTY-0003") {
        id
        name
        brand
        category
        image {
            url
        }
    }
}
**Sample Error Response**

{
  "errors": [
    {
      "message": "Product with ID 'TTY-0003' not found.",
      "locations": [
        {
          "column": 5,
          "line": 2
        }
      ],
      "path": [
        "product"
      ],
      "extensions": {
        "classification": "NOT_FOUND"
      }
    }
  ],
  "data": {
    "product": null
  }
}

```
## **Testing**
### How to Run Unit Tests:
The application uses **JUnit 5** and **Mockito** for unit testing. To run tests:
``` bash
mvn test
```
### Coverage:
- Tests included for:
    - **Service Layer** (e.g., `ProductService`)
    - **Mapper Layer** (e.g., `ProductMapper`)
    - **SPARQL Query Handling**

- Test cases also include **negative scenarios** such as:
    - Missing or null bindings.
    - SPARQL query failures.


## **Folder Structure**
``` bash
src
├── main
│   ├── java
│   │   └── com.myretail
│   │       ├── controller    # Controllers
│   │       ├── service       # Business Logic
│   │       ├── mapper        # Mapper for SPARQL Binding to Models
│   │       └── model         # Domain Models
│   └── resources
│       ├── application.properties  # Configuration
│       └── products_schema,products - loaded owl schema and RDF data 
└── test
    └── java
        └── com.myretail       # Unit Tests and Integration Test
```
