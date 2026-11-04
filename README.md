# Library API - IN3101 Lab Sheet 02

A Spring Boot REST API for managing books (CRUD) backed by MySQL, documented with Swagger/OpenAPI.
Index number: 235083B

## Technologies
Java 21, Spring Boot 4.1.2, Spring Web MVC, Spring Data JPA (Hibernate), Jakarta Validation,
MySQL 8, springdoc-openapi 3.1.1, Maven Wrapper.

## Prerequisites
- JDK 21 (`JAVA_HOME` set), MySQL 8 running, Postman

## Configure MySQL
1. Create the database (MySQL Workbench): `CREATE DATABASE IF NOT EXISTS library_db;`
2. Provide credentials through environment variables (never commit them):
   ```powershell
   $env:DB_PASSWORD = "your_root_password"
   # optional: $env:DB_USERNAME (default root), $env:DB_URL (default jdbc:mysql://localhost:3306/library_db)
   ```
   Alternatively copy `application-local.properties.example` to `application-local.properties` (git-ignored).

## Run (Windows PowerShell)
```powershell
.\mvnw.cmd spring-boot:run
```
Tests: `.\mvnw.cmd clean test` (`BookControllerTest` needs no database; `LibraryApiApplicationTests` needs MySQL).

## Endpoints
| Method | URL | Action | Success |
|---|---|---|---|
| GET | /api/books | List all books | 200 |
| GET | /api/books/{id} | Get one book | 200 (404 if missing) |
| POST | /api/books | Create | 201 (400 if invalid) |
| PUT | /api/books/{id} | Update | 200 (404 if missing) |
| DELETE | /api/books/{id} | Delete | 204 (404 if missing) |

Book fields: `title` (required), `author` (required), `isbn`, `price` (>= 0).

## Swagger
http://localhost:8080/swagger-ui/index.html (also `/swagger-ui.html`); OpenAPI JSON at `/v3/api-docs`.

## Testing with Postman
Import `postman/IN3101_Lab02_Library_API.postman_collection.json` and run the requests in order.
Screenshot instructions: `docs/SCREENSHOT_CHECKLIST.md`.
