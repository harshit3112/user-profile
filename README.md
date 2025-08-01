# User Profile Management System

A multi-module Spring Boot application for managing user profiles with REST APIs, built using Maven and Java 17.

## Project Structure

The project is organized into 4 modules:

### 1. user-profile-controller
- Contains REST controllers and main application class
- Handles HTTP requests and responses
- Includes Swagger configuration for API documentation
- Contains global exception handler

### 2. user-profile-service
- Contains business logic layer
- Service interfaces and their implementations
- Handles data transformation between DTOs and entities

### 3. user-profile-model
- Contains DTOs (Data Transfer Objects)
- API response models
- Validation annotations

### 4. user-profile-repository
- Contains JPA entities and repositories
- Database layer with PostgreSQL database
- Entity definitions and database queries

## Technology Stack

- **Java**: 17
- **Framework**: Spring Boot 3.2.0
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Documentation**: Swagger/OpenAPI 3
- **Validation**: Jakarta Validation
- **ORM**: Spring Data JPA with Hibernate

## Features

- Create new user profiles
- Retrieve user profiles by ID
- Input validation with proper error handling
- Swagger UI for API documentation
- PostgreSQL database integration
- Global exception handling
- Standardized API responses

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

## How to Run

1. **Setup PostgreSQL Database**
   ```bash
   # Create database
   createdb userprofiledb
   
   # Or using psql
   psql -U postgres
   CREATE DATABASE userprofiledb;
   ```

2. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd user-profile
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   cd user-profile-controller
   mvn spring-boot:run
   ```

   Or run the main class directly:
   ```bash
   java -jar user-profile-controller/target/user-profile-controller-1.0.0.jar
   ```

5. **Access the application**
   - **Base URL**: http://localhost:8087/user-profile
   - **Swagger UI**: http://localhost:8087/user-profile/swagger-ui.html
   - **Database**: PostgreSQL on localhost:5432/userprofiledb

## API Endpoints

### Create User
- **POST** `/v1/user/create`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "address": "123 Main St, New York, NY"
  }
  ```

### Get User
- **GET** `/v1/user/{userId}`
- **Path Parameter**: `userId` (Long)

## Sample API Responses

### Success Response
```json
{
  "success": true,
  "message": "User created successfully",
  "data": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "address": "123 Main St, New York, NY"
  },
  "errorCode": null
}
```

### Error Response
```json
{
  "success": false,
  "message": "User not found with id: 999",
  "data": null,
  "errorCode": "USER_NOT_FOUND"
}
```

## Database Schema

### user_details table
- `id` (BIGINT, Primary Key, Auto-increment)
- `first_name` (VARCHAR(50), Not Null)
- `last_name` (VARCHAR(50), Not Null)
- `email` (VARCHAR(100), Not Null, Unique)
- `phone_number` (VARCHAR(15))
- `address` (VARCHAR(255))

## Testing

The application comes with sample data pre-loaded. You can test the APIs using:

1. **Swagger UI** - Interactive API documentation
2. **Postman** - Import the API endpoints
3. **cURL** commands:

   ```bash
   # Create a user
   curl -X POST http://localhost:8087/user-profile/v1/user/create \
     -H "Content-Type: application/json" \
     -d '{
       "firstName": "Test",
       "lastName": "User",
       "email": "test.user@example.com",
       "phoneNumber": "1111111111",
       "address": "Test Address"
     }'

   # Get a user
   curl -X GET http://localhost:8087/user-profile/v1/user/1
   ```

## Architecture Principles

This project follows SOLID principles and best practices:

- **Single Responsibility**: Each class has a single, well-defined purpose
- **Open/Closed**: Code is open for extension but closed for modification
- **Liskov Substitution**: Interfaces can be substituted with their implementations
- **Interface Segregation**: Small, focused interfaces
- **Dependency Inversion**: Depends on abstractions, not concretions

## Additional Features

- **Validation**: Input validation with custom error messages
- **Exception Handling**: Global exception handler for consistent error responses
- **Logging**: Comprehensive logging configuration
- **Health Checks**: Spring Boot Actuator endpoints
- **Documentation**: Auto-generated API documentation with Swagger

## Development Notes

- The application uses PostgreSQL database for persistent data storage
- Database schema is auto-created/updated on startup using Hibernate DDL
- Sample data is loaded automatically from data.sql
- All API responses follow a consistent format
- Proper HTTP status codes are used for different scenarios
- Database connection details can be configured in application.yaml
