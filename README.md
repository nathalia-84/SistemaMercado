
---

# SistemaMercado: Backend System for Inventory and Order Management

## Overview

The `SistemaMercado` is a backend application developed in **Java** using the **Spring Boot** framework. It simulates a supermarket management system with a focus on clients, products, and orders. The system is designed with a clean architecture that separates concerns across different layers, ensuring scalability, maintainability, and adherence to best practices.

## Project Structure

The project follows a modular architecture organized into packages, each responsible for specific functionality:

```
br.ufrn.imd.sistemamercado
├── controllers     // REST Controllers for handling HTTP requests
├── dto             // Data Transfer Objects for request/response models
├── exceptions      // Custom exceptions and global error handling
├── model           // JPA Entities representing database tables
├── repositories    // Interfaces for data access using Spring Data JPA
└── services        // Business logic layer
```

---

## Key Components

### 1. **Controllers**

The `controllers` package contains REST endpoints to expose system functionalities. These controllers are responsible for handling HTTP requests and responses.

- **ClienteController**: Manages clients (creation, retrieval, updating, deletion).
- **ProdutoController**: Handles product operations (creation, inventory management, logical deletion).
- **PedidoController**: Deals with orders (CRUD operations, processing).

Each controller interacts with the **service layer** and delegates data access tasks to the **repository layer**.

---

### 2. **DTOs (Data Transfer Objects)**

The `dto` package contains classes used to transfer data between the application layers and the API endpoints.

- **ClienteDTO**: Defines fields required for creating/updating client data.
- **ProdutoDTO**: Defines fields required for creating/updating product data.
- **PedidoDTO**: Defines fields required for creating/updating order data.

These DTOs ensure that the internal structure of entities is hidden and API requests/responses are well-defined and consistent.

---

### 3. **Exception Handling**

- **ResourceNotFoundException**: A custom exception thrown when a requested resource (client, product, or order) is not found.
- **GlobalExceptionHandler**: A centralized error-handling mechanism that captures exceptions thrown across the application and maps them to appropriate HTTP responses (e.g., 404 for resource not found).

---

### 4. **Models (Entities)**

The `model` package contains **JPA entities** that map directly to the application's database tables:

- **ClienteEntity**: Represents a client, including fields like `name`, `CPF`, `gender`, and `active` status.
- **ProdutoEntity**: Represents a product, with fields for `name`, `quantity`, `price`, and `active` status.
- **PedidoEntity**: Represents an order, associating clients with products and their quantities.

Each entity is annotated with JPA mappings for seamless integration with the database.

---

### 5. **Repositories**

The `repositories` package contains **Spring Data JPA** interfaces for data access:

- **ClienteRepository**
- **ProdutoRepository**
- **PedidoRepository**

These repositories abstract database operations, enabling developers to perform CRUD operations using high-level methods without writing SQL queries.

---

### 6. **Services**

The `services` package contains the business logic for the system. Each service is responsible for implementing specific use cases while coordinating between the controller and repository layers:

- **ClienteService**: Business logic for managing clients, including activation/deactivation.
- **ProdutoService**: Handles product inventory management and logical deletions.
- **PedidoService**: Processes orders, including validation of client and product availability.

---

## Functionality

The system provides the following key features:

### **Client Management**
- Create, update, and retrieve client data.
- Perform soft deletes (logical deactivation) to maintain historical data.

### **Product Inventory**
- Manage product details, including stock and price.
- Support for logical deletion of inactive products.

### **Order Processing**
- Link orders to specific clients and products.
- Validate stock availability during order creation.

---

## Technical Highlights

- **Spring Boot**: Framework for building robust and scalable web applications.
- **Spring Data JPA**: Simplifies database access with built-in repository interfaces.
- **Jakarta Bean Validation**: Ensures data consistency using annotations like `@Valid`.
- **Exception Handling**: Centralized error management with custom exceptions and global handlers.

---

## How to Run the Application

1. **Prerequisites**
  - Java 21+
  - Maven
  - MySQL

2. **Steps**
  - Clone the repository:
    ```bash
    git clone <repository-url>
    ```
  - Navigate to the project directory:
    ```bash
    cd sistemamercado
    ```
  - Configure database settings in `application.properties`.
  - Build and run the application
  - Access the API at `http://localhost:8080`.

---

## Example Endpoints

- **Client API**
  - GET `/clientes/`: Retrieve all active clients.
  - POST `/clientes/`: Create a new client.
  - DELETE `/clientes/dl/{id}`: Soft-delete a client.

- **Product API**
  - GET `/produtos/`: Retrieve all active products.
  - PUT `/produtos/{id}`: Update product details.

- **Order API**
  - POST `/pedidos/`: Create a new order.
  - GET `/pedidos/{id}`: Retrieve an order by ID.

---


