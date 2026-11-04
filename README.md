# Library Management REST API

**Module:** IN3101 - Enterprise Application Development
**Lab:** Lab Sheet 02 - Building a REST API with Spring Boot
**Student index number:** 235083B

## Project overview
A RESTful API for managing library books. It supports creating, reading, updating and deleting books
(CRUD), stores data in MySQL through Spring Data JPA, validates input with Jakarta Bean Validation and
is documented with Swagger UI (OpenAPI).

Layers: `controller` -> `service` -> `repository` -> MySQL. Base package: `lk.ac.mrt.fit.library_api`.

## Technologies used
- Java 21
- Spring Boot 4.1.2 (Spring Web MVC, Spring Data JPA, Validation, DevTools)
- MySQL 8 with the MySQL Connector/J driver
- springdoc-openapi 3.1.1 (Swagger UI)
- Maven (Maven Wrapper included)
- Postman for API testing

## Prerequisites
- JDK 21 with `JAVA_HOME` set
- MySQL Server 8 running on `localhost:3306`
- Maven is not required separately; use the included `mvnw.cmd`
- Postman (for manual testing)

## Database setup
Run in MySQL Workbench or the `mysql` client:
```sql
CREATE DATABASE IF NOT EXISTS library_db;
```
Hibernate creates the `book` table automatically (`spring.jpa.hibernate.ddl-auto=update`).

## Configure database credentials securely
Credentials are never stored in the repository. `application.properties` reads them from environment variables:

| Variable | Purpose | Default |
|---|---|---|
| `DB_PASSWORD` | MySQL password | none (required) |
| `DB_USERNAME` | MySQL user | `root` |
| `DB_URL` | JDBC URL | `jdbc:mysql://localhost:3306/library_db` |

PowerShell (current terminal session):
```powershell
$env:DB_PASSWORD = "your_mysql_password"
```
Alternatively, copy `application-local.properties.example` to `application-local.properties` and set the
password there. That file is listed in `.gitignore` and must not be committed.

## Run the application (Windows PowerShell)
```powershell
.\mvnw.cmd spring-boot:run
```
The API starts on http://localhost:8080. Run the tests with `.\mvnw.cmd clean test`.
`BookControllerTest` does not need a database; `LibraryApiApplicationTests` needs MySQL and `DB_PASSWORD`.

## API endpoints
| Method | URL | Description | Success status |
|---|---|---|---|
| GET | `/api/books` | List all books | 200 OK |
| GET | `/api/books/{id}` | Get one book | 200 OK (404 if not found) |
| POST | `/api/books` | Create a book | 201 Created (400 if invalid) |
| PUT | `/api/books/{id}` | Update a book | 200 OK (404 if not found) |
| DELETE | `/api/books/{id}` | Delete a book | 204 No Content (404 if not found) |

Book fields: `title` (required), `author` (required), `isbn`, `price` (zero or more).

Example request body:
```json
{
  "title": "235083B - Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "price": 4500.00
}
```
Validation errors return `400 Bad Request` with a `fieldErrors` object naming the invalid fields.

## Swagger documentation
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Test with Postman
1. Start the application.
2. In Postman, choose **Import** and select `postman/IN3101_Lab02_Library_API.postman_collection.json`.
3. Run the requests in order. The collection stores created book ids in variables.
4. Screenshot instructions are in `docs/SCREENSHOT_CHECKLIST.md`.
