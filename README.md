# Shopping Platform

This is a Spring Boot application for the Shopping Platform. It uses PostgreSQL as its database and Flyway for database
migrations.

## Prerequisites

- Docker and Docker Compose
- Java 21
- Gradle

## Run locally

### 1. Setup database (PostgreSQL) container

```bash
docker-compose up
```

### 2. Run application

```bash
./gradlew bootRun
```

## Run tests

There is no need to set up docker containers for tests,
since [testcontainers](https://testcontainers.com/) is used to run PostgreSQL in Docker container for testing puropose.

```bash
 ./gradlew test
 ```

## Project Overview - Assumptions

This project is composed of two main modules: `products` and `discounts`. Each module is responsible for a specific
domain within the application, but they are not strictly separated from each other. This choice has been made
deliberately to maintain simplicity and flexibility in the current stage of development.

### Modules

#### 1. Products Module

The `products` module is responsible for handling all operations related to product data. Currently, this module follows
a 2-layered architecture.

##### Architectural Choice

**2-Layered Architecture (API Layer & Persistence Layer)**

- **Reasoning**:
    - The `products` module currently only exposes `GET /products/{id}` endpoints, which serve data already existing in
      the system.
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
      than just simple data retrieval, we may introduce a service layer to handle more complex business logic.
    - If `products` exists somewhere in the system, it might be considered to be refactored towards using some external
      API
    - This approach will allow us to scale the module’s architecture in a controlled manner, ensuring that we do not
      prematurely over-engineer the solution.
