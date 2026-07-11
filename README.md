# Employee Organization Management System

A Spring Boot RESTful API for managing employees and departments within an organization. The project follows a clean layered architecture and demonstrates industry-standard backend development practices including DTO mapping, validation, centralized exception handling, and CI/CD integration using Jenkins.

## Features

- Employee CRUD operations
- Department CRUD operations
- Layered Architecture (Controller → Service → Repository)
- DTO Pattern for request and response handling
- Entity-DTO mapping using ModelMapper
- Custom Validation using Bean Validation
- Global Exception Handling with `@RestControllerAdvice`
- Standardized API response wrapper
- Spring Data JPA & Hibernate
- MySQL Database Integration
- RESTful API Design
- Jenkins CI/CD Pipeline with GitHub Webhooks
- Maven Build Automation

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entities
├── dto
├── configs
├── advice
├── annotations
└── exceptions
```

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- ModelMapper
- Jakarta Validation
- Lombok
- Jenkins
- Git & GitHub

## Jenkins Integration

The project includes a Jenkins Pipeline that automates the build process.

- Automatically triggers on GitHub push
- Checks out the latest code
- Builds the project using Maven
- Runs automated tests
- Reports build status

## Future Improvements

- JWT Authentication & Authorization
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing
- Pagination & Sorting
- Caching with Redis
- SonarQube Integration
