# Spring Boot JWT

**A database-backed authentication exercise with registration, BCrypt password checks and signed bearer tokens.**

This Java/Spring Boot project explores Spring Security filters, MyBatis user lookup and PostgreSQL role associations. It is a learning implementation, not an audited authentication service or a verified deployment.

## Implemented flow

- Registration checks for an existing email, hashes the password and inserts a user.
- Registration attempts to attach ROLE_USER when that role exists.
- Login checks credentials and returns an AuthResponse containing token.
- JwtService signs HS256 tokens with an email subject, userId claim and five-hour validity.
- JwtAuthFilter loads user details and authorities from the database for bearer-token authentication.

Roles are loaded as authorities, but the supplied security rules only require authentication outside public auth/documentation paths. No role-specific endpoint restrictions or method-security configuration are supplied. There is also no separate protected business controller to demonstrate those permissions.

## Stack

The POM declares Java 17, Spring Boot 4.0.5, MyBatis 4.0.1, JJWT 0.11.5, Springdoc 2.8.0, Lombok and ModelMapper. The Maven wrapper selects Maven 3.9.14. These are source declarations, not a verified compatible dependency set.

## Before running

Use an isolated PostgreSQL database and synthetic accounts. The supplied properties target user_db on local port 5432. Database credentials are committed, and JwtService contains a hard-coded signing key. Replace/rotate any live values before use; this documentation neither repeats nor removes them.

The supplied schema is not a verified ready-to-run setup:

- A seed-user INSERT lacks a terminating semicolon before the next INSERT.
- The seeded password is plaintext, unlike the BCrypt format expected by login.
- users.email has no uniqueness constraint despite the registration pre-check.
- Tables are created without rerun guards, and role assignments assume fixed IDs.

Do not rely on the seeded account as a working login. Review and correct schema/configuration separately before expecting successful startup or requests. No Docker configuration, migration tool or environment template is supplied. No environment-backed signing-key setting is implemented in JwtService.

After resolving prerequisites, proposed commands from the repository root are:

```powershell
.\mvnw.cmd spring-boot:run
```

POSIX equivalent: `sh mvnw spring-boot:run`. Initial use may download Maven and dependencies. These commands were not executed during this refresh. OpenAPI configuration and public Swagger paths are supplied, but actual documentation availability was not checked.

## API

Base path: `/api/v1/auth`.

| Method | Route | Controller result |
| --- | --- | --- |
| POST | `/register` | HTTP 201 text on success; 409 for an existing email; caught failures return 500 text |
| POST | `/login` | HTTP 200 token object on success; caught failures return 401 text |

RegisterRequest contains fullName, email and password. AuthRequest contains email and password. No request validation annotations or @Valid handling are supplied. Responses are not a uniform envelope.

Registration performs user and role inserts without a supplied transaction boundary. Missing ROLE_USER silently skips role assignment. The duplicate-email pre-check does not establish concurrency-safe uniqueness.

## Source map

- [AuthController.java](src/main/java/com/example/spring_jwt/controller/AuthController.java): registration and login orchestration.
- [JwtService.java](src/main/java/com/example/spring_jwt/jwt/JwtService.java): signing, parsing and expiry checks.
- [JwtAuthFilter.java](src/main/java/com/example/spring_jwt/jwt/JwtAuthFilter.java): bearer-token request processing.
- [AppUserRepository.java](src/main/java/com/example/spring_jwt/repository/AppUserRepository.java): annotated SQL and role mapping.
- [SecurityConfig.java](src/main/java/com/example/spring_jwt/configuration/SecurityConfig.java): public paths and authentication requirement.

The role lookup returns String while its SQL can return multiple roles and its caller maps a collection. Multi-role loading needs an actual MyBatis/database test. The filter does not catch token-parsing failures locally. No logout, revocation, refresh-token flow, rate limiter or account lockout is supplied. SecurityConfig does not explicitly configure stateless session management.

## Checks

Proposed Windows commands:

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
```

POSIX equivalents use `sh mvnw test` and `sh mvnw package`. The supplied test is a contextLoads smoke test, not authentication or SQL coverage. No Maven download, compilation, tests, database operation or HTTP request was performed during this refresh.

See [CONTRIBUTING.md](CONTRIBUTING.md), [SECURITY.md](SECURITY.md) and [refresh notes](docs/repository-refresh.md). Application code and configuration are unchanged.

## Licence

No root project licence file is supplied. Wrapper notices do not establish an application licence; this refresh assigns none.
