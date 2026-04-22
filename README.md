### About
A Spring Boot REST API implementing JSON Web Token (JWT) authentication and role-based access control. The system utilizes Spring Security for request filtering, MyBatis for data persistence, and PostgreSQL for relational data storage. 

***

# Spring JWT Authentication System

## Overview
This repository contains a backend authentication service handling user registration, credential verification, and secure token generation. It features a custom `JwtAuthFilter` for request interception and utilizes an MVC architecture.

## Technical Stack
* **Language:** Java 17
* **Framework:** Spring Boot 4.0.5
* **Security:** Spring Security, JJWT (0.11.5)
* **Data Access:** MyBatis Spring Boot Starter (4.0.1)
* **Database:** PostgreSQL
* **API Documentation:** Springdoc OpenAPI / Swagger UI
* **Utilities:** Lombok, ModelMapper
* **Build Tool:** Maven Wrapper

## Database Configuration
The application requires a PostgreSQL instance. Default configuration in `application.properties`:
* **URL:** `jdbc:postgresql://localhost:5432/user_db`
* **Username:** `postgres`
* **Password:** `12345`

### Schema 
The database structure is defined in `src/main/resources/schema.sql` and includes:
* `users` table: `id`, `full_name`, `email`, `password` (BCrypt encoded).
* `roles` table: `id`, `role_name` (e.g., `ROLE_USER`, `ROLE_ADMIN`).
* `user_role` table: Mapping table for user-role relationships.

## API Endpoints

**Base Path:** `/api/v1/auth`

| Method | Endpoint | Description | Request Body | Response |
| :--- | :--- | :--- | :--- | :--- |
| POST | `/register` | Registers a new user and assigns `ROLE_USER`. | `{"fullName": "...", "email": "...", "password": "..."}` | `201 Created` / `409 Conflict` |
| POST | `/login` | Authenticates credentials and returns a signed JWT. | `{"email": "...", "password": "..."}` | `200 OK` + Token / `401 Unauthorized` |

*Note: All endpoints outside `/api/v1/auth/**` and Swagger paths require a valid Bearer token in the `Authorization` header.*

## API Documentation
Interactive API documentation is generated automatically. Upon running the application, access:
* **Swagger UI:** `http://localhost:8080/swagger-ui.html`
* **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

## Security Specifications
* **Password Hashing:** `BCryptPasswordEncoder`
* **JWT Algorithm:** HMAC SHA-256 (HS256)
* **Token Validity:** 5 hours (18,000 seconds)
* **Auth Header Format:** `Authorization: Bearer <token>`

## Execution
Use the included Maven wrapper to build and run the application:
```bash
./mvnw spring-boot:run
```
