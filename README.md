# Shopping Platform

This is a Spring Boot application for the Shopping Platform. It uses PostgreSQL as its database and Flyway for database
migrations.

## Prerequisites

- Docker and Docker Compose
- Java 21
- Gradle

## Run Locally

### 1. Set up the Database (PostgreSQL) Container

```bash
docker-compose up
```

### 2. Run the Application

```bash
./gradlew bootRun
```

## Run Tests

There is no need to set up Docker containers for tests since [Testcontainers](https://testcontainers.com/) is used to
run PostgreSQL in a Docker container for testing purposes.

```bash
./gradlew test
```

## Project Overview

This project is composed of two main modules: `products` and `pricing`. Each module is responsible for a specific domain
within the application, and they are designed to be different bounded contexts, making it easy to extract each into a
separate microservice.

### Modules

#### 1. Products Module

The `products` module is responsible for handling all operations related to product data. Currently, this module follows
a 2-layered architecture.

##### Architectural Choice

**2-Layered Architecture (API Layer & Persistence Layer)**

- **Reasoning**:
    - The `products` module currently exposes 1 endpoint: `GET /api/products/{id}`, which serves data already existing
      in the system.
    - Since the operations within this module are limited to simple data retrieval without complex business logic or
      multi-step processes, a service layer was deemed unnecessary at this time.
    - This decision keeps the module lightweight and easy to maintain, while still providing a clear separation between
      the API (controller) and the persistence (repository) layers.

- **Current Structure**:
    - **API Layer**: Handles incoming HTTP requests, interacts with the persistence layer to fetch the necessary data,
      and returns it in the appropriate format.
    - **Persistence Layer**: Manages the interaction with the database, specifically retrieving product data using
      repositories.

- **Future Considerations**:
    - As the complexity of the `products` module grows, particularly if new endpoints are introduced that require more
      than just simple data retrieval, we may introduce a service layer or consider some more advanced architecture
      (e.g. hexagonal) to handle more complex business logic.

### 2. Pricing Module

The `pricing` module is responsible for all operations related to pricing calculations, and it has been designed using
the **Hexagonal Architecture**. This approach ensures that the core business logic is isolated from external
dependencies, making the module highly maintainable and adaptable to changes.

#### Architectural Choice

**Hexagonal Architecture**

- **Reasoning**:
    - The core of the `pricing` module resides in the `domain` package, which is completely framework-agnostic. This
      allows the business logic to remain independent of any external technologies or libraries, making it easier to
      adapt or extend the module in the future.
    - All external interactions, including configuration and integration with other modules, are handled through
      adapters. This clean separation ensures that changes in external systems or requirements can be managed with
      minimal impact on the core logic.

- **Current Structure**:
    - **Domain Layer**: This is the heart of the module, containing all business rules and logic. It is isolated from
      external dependencies, ensuring that it remains flexible and maintainable.
    - **Adapters (API, External.Products, Persistence)**: Contain implementations of the ports defined in the `domain`
      package. These adapters handle the interactions with external systems (e.g., database, HTTP clients). They also
      include configurations of Spring beans and other framework-specific components defined in these directories, such
      as RestControllers in the API layer.
    - **Application Layer**: Ties all pieces together. Includes beans configuration of domain services used in this
      layer. There is one **Application Service**: `PriceCalculationService`, which manages the overall pricing
      calculation process. It orchestrates interactions between the domain logic and external systems, ensuring that the
      correct prices are calculated and applied. Discount setup is configured in the application layer
      through `DiscountInitializer`, with the actual configuration stored in `discounts.yml`.

- **Discount Management**:
    - **Configuration**: Discounts are managed via the `discounts.yml` file, which is loaded at application startup.
      The `DiscountInitializer` reads this configuration and persists the discount data to the database.
    - **Discount Policies**:
        - **Count-Based Discount**: This type of discount applies to specific products based on the quantity purchased.
          For example, buying a certain number of a product may trigger a discount on that product.
        - **Fixed Percentage Discount on Cart**: This discount applies a fixed percentage reduction to the final price
          of the entire cart, making it a broad discount that affects the total order value.
    - **Test Environment**: A separate `discounts.yml` file is used for testing, allowing for different discount
      configurations between test and production environments. This ensures that tests are isolated and reflective of
      real-world scenarios.

- **Future Considerations**:
    - The hexagonal architecture allows the `pricing` module to evolve independently. Future enhancements, such as
      adding new pricing strategies or integrating with additional external services, can be accomplished with minimal
      changes to the core domain logic.
