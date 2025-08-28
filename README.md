# Mini Shop
### Java Microservices Project 
<sub> (Spring Boot, MySQL, MongoDB, Eureka, Feign, Swagger) </sub>

## 📌 Project Overview
This is a **Java-based Microservices architecture** project built with **Spring Boot**.  
It demonstrates inter-service communication, service discovery, and database integration using **MySQL** and **MongoDB**.  

We have **three services**:
1. **Eureka Server** – Service Registry for discovery.
2. API Gateway – Central entry point for routing client requests to microservices
3. **Product Service** – Manages products (CRUD + stock management) using **MySQL**.
4. **Order Service** – Places orders using **MongoDB** and communicates with Product Service via **Feign Client**.

---

## 🏗 Architecture Diagram
```
         +----------------+
         | Swagger (API UI)|
         +----------------+
                  |
                  ↓
           +---------------+
           | API Gateway   |
           +---------------+
            |            |
            ↓            ↓
   +----------------+  +----------------+
   | Product Service|  | Order Service  |
   | (MySQL, JPA)   |  | (MongoDB)      |
   +----------------+  +----------------+
           ↑
           |
    +--------------+
    | Eureka Server|
    +--------------+


```

- **Service Discovery:** Eureka ensures that services can find each other dynamically (no hardcoded URLs).
- **API Gateway:** Acts as a single entry point for clients, routing requests to respective microservices.
- **Inter-service Calls:** Handled by Feign Client.
- **Databases:** MySQL for Product Service, MongoDB for Order Service.
- **API Documentation:** Swagger UI / OpenAPI.

---

## 🛠 Technologies Used
- **Java 17**
- **Spring Boot 3.x**
- **Spring Cloud (Eureka, Feign)**
- **Spring Data JPA** (for MySQL)
- **Spring Data MongoDB** (for MongoDB)
- **Swagger / OpenAPI**
- **Maven**
- **MySQL 8+**
- **MongoDB 6+**

---

## 📂 Project Structure
```
microservices-project/
│
├── eureka-server/ # Service Discovery Server
│
├── api-gateway/   # API Gateway (Spring Cloud Gateway)
│
├── product-service/ # Product Management Service (MySQL)
│
├── order-service/   # Order Management Service (MongoDB, Feign client to Product Service)
│
└── README.md        # Project documentation

```

---

## ⚙ Prerequisites
Before running this project, ensure you have:
- **Java 17** installed → `java -version`
- **Maven 3+** installed → `mvn -v`
- **MySQL** running and accessible
- **MongoDB** running and accessible
- **Git** installed → `git --version`

---

## 🗄 Database Setup

### MySQL (Product Service)
Update product-service/src/main/resources/application.properties:
```
# MySQL Config
spring.datasource.url=jdbc:mysql://localhost:3306/productdb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```
### 🗄 MongoDB (Order Service)

1. Start MongoDB server.
2. Update order-service/src/main/resources/application.properties:
```
# mongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/orderdb

# Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

```
Eureka Dashboard → http://localhost:8761

📖 API Docs

## Swagger UI:

- Product: http://localhost:8081/swagger-ui.html
- Order: http://localhost:8082/swagger-ui.html

## API Gateway
Clients should access services via the gateway:
- Product Service: http://localhost:8080/products/...
- Order Service: http://localhost:8080/orders/...

## Product Service:

- POST /products – Create product
- GET /products – Get all
- GET /products/{id} – Get by ID
- PUT /products/{id} – Update
- DELETE /products/{id} – Delete
- POST /products/{id}/reserve – Reserve stock
- POST /products/{id}/release – Release stock

## Order Service:

- POST /orders – Place order (checks stock)
- GET /orders – Get all orders
- GET /orders/{id} – Get by ID

## 🔗 Flow

- Client → API Gateway → routes request to respective microservice.
- Order Service → calls Product Service (Feign) to reserve stock.
- Product Service → updates stock in MySQL.
- Order Service → saves order in MongoDB.





