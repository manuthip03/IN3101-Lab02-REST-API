# Screenshot checklist (S1-S8)

Capture the **whole Postman window** each time (method, URL, body and status must be visible).
Import `postman/IN3101_Lab02_Library_API.postman_collection.json` into Postman first.
Save images in `docs/screenshots/` as `S1.png` ... `S8.png`.

Prerequisites: MySQL80 running, `library_db` exists, `DB_PASSWORD` set, app running (`.\mvnw.cmd spring-boot:run`).

Run the requests in collection order (top to bottom):

| # | Request | What must be visible |
|---|---------|----------------------|
| S1 | "S1 - Create a book (201)" | POST /api/books, JSON body, **201 Created**, response with id |
| (run "Create second book" and "Create third book" - no screenshot) | | |
| S2 | "S2 - Get all books (200)" | GET /api/books, **200 OK**, 3+ books incl. `235083B - Clean Code` |
| S3 | "S3 - Get book by ID (200)" | GET /api/books/{id}, **200 OK**, correct book |
| S4 | "S4 - Update a book (200)" | PUT, changed body, **200 OK**, updated values |
| S5 | "S5 - Delete a book (204)" | DELETE /api/books/{id}, **204 No Content** |
| S6 | "S6 - Get deleted book (404)" | same id again, **404 Not Found** |
| S7 | "S7 - Create book with empty title (400)" | POST with `"title": ""`, **400 Bad Request** |
| S8 | Browser: http://localhost:8080/swagger-ui/index.html | POST /api/books expanded: summary "Create a book", description, 201 and 400 responses |

Tip: if the Swagger "Try it out" call is needed, run it once from Swagger as well (lab D.3).

## Building the PDF `235083B_IN3101_Lab02.pdf`
1. Open Word/Google Docs. Page 1: your **name**, index **235083B**, GitHub repo link.
2. Add headings S1 ... S8, each with its screenshot underneath.
3. Export as PDF named `235083B_IN3101_Lab02.pdf` and submit on the LMS (due 4/11/2026).
