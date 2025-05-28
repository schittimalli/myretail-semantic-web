# 🛍️ myretail-semantic-web

A Spring Boot application that serves product data using **GraphQL**, powered by **Apache Jena** and **RDF4J**. The application uses semantic web standards (OWL, RDF) to describe and query BestBuy-style product information.


This expose 2 end points 
1) to Get ALL products
2) TO product based on ID
---

## 📡 GraphQL Endpoint

**This is to get ALL Products**

**URL:** `http://localhost:8080/graphql`

---

## 🧪 Sample Query

```graphql
query {
  products {
    name
    brand
    image {
      url
    }
  }
}
```

---

## 📥 Sample Response

```json
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
     


---

## 📡 GraphQL Endpoint

**get products based on ID**

 

---

**URL:** `http://localhost:8080/graphql`

---

## 🧪 Sample Query

```graphql

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
```

---

## 📥 Sample Response

```json
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



---

**This application includes Unit test cases and integration test cases as well-**

**Unit test**
ProductServiceTest
ProductControllerTest


**Integration test here  :**
ProductGraphQLIntegrationTest