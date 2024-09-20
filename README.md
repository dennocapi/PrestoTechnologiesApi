# Presto Technologies API

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Accessing Swagger UI](#accessing-swagger-ui)
- [Docker Support](#docker-support)
- [Unit Testing](#unit-testing)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Description

The Presto Technologies API is a Spring Boot application designed for managing various functionalities within the Presto Technologies ecosystem. This application provides RESTful services, user authentication, and an easy-to-use API documentation interface through Swagger.

## Features

- User authentication and authorization
- CRUD operations for data management
- Comprehensive RESTful API endpoints
- Swagger UI for easy API documentation and testing
- Unit tests for service verification

## Technologies Used

- Java: Version 17
- Spring Boot: Version 3.3.4
- Spring Data JPA: For database interactions
- Spring Security: For securing the API
- H2 Database: For in-memory data storage
- Springdoc OpenAPI: For API documentation
- Maven: For project management and dependency management

## Prerequisites

Before you begin, ensure you have met the following requirements:

- JDK 17 or higher installed on your machine
- Maven installed for building the project
- Docker installed (optional, for containerization)

## Installation

1. Clone the repository:

   ```bash
   git clone  https://github.com/dennocapi/PrestoTechnologiesApi.git
   cd prestoTech
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

## Running the Application

1. Start the application:

   ```bash
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8080`.

## Accessing Swagger UI

To view and interact with the API documentation, navigate to:

```
http://localhost:8080/swagger-ui/index.html
```

## Docker Support

The application can be run inside a Docker container. Here’s how to set it up:

1. Create a `Dockerfile` in the root of your project with the following content:

   ```dockerfile
   # Use the official Maven image to build the app
   FROM maven:3.8.5-openjdk-17 AS builder
   WORKDIR /app
   COPY . .
   RUN mvn clean package -DskipTests

   # Use the official OpenJDK image to run the app
   FROM openjdk:17-jdk-slim
   COPY --from=builder /app/target/prestoTech-0.0.1-SNAPSHOT.jar prestoTech.jar
   ENTRYPOINT ["java", "-jar", "/prestoTech.jar"]
   ```

2. Build the Docker image:

   ```bash
   docker build -t prestotech .
   ```

3. Run the Docker container:

   ```bash
   docker run -p 8080:8080 prestotech
   ```

   The application will be accessible at `http://localhost:8080`.

## Unit Testing

To run unit tests and ensure the application works as expected, execute:

```bash
mvn test
```

## API Endpoints

Here’s a brief overview of the available endpoints:

| Method | Endpoint                | Description               |
|--------|-------------------------|---------------------------|
| GET    | /api/customers          | Retrieve all customers    |
| POST   | /api/customers/register | Create a new customer     |
| GET    | /api/customers/{id}     | Retrieve a customer by ID |
| PUT    | /api/customers/{id}     | Update a customer by ID   |
| DELETE | /api/customers/{id}     | Delete a customer by ID   |

## Contributing

Contributions are welcome! If you’d like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```
