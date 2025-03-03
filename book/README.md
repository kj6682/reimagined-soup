# Book API

This project provides a RESTful API for managing books. The API supports the following operations:

- **GET** a list of books
- **GET** a book by ISBN
- **POST** a new book
- **PUT** an existing book
- **DELETE** a book by ISBN

## Prerequisites

- Ensure you have `httpie` installed. You can install it using pip:
  ```sh
  pip install httpie
  ```

## API Endpoints

### Get a List of Books

Retrieve a list of all books.

```sh
http GET http://localhost:8080/api/books
```

### Get a Book by ISBN

Retrieve a book by its ISBN.

```sh
http GET http://localhost:8080/api/books/9780060935467
```

### Create a New Book

Create a new book.

```sh
http POST http://localhost:8080/api/books isbn=9780060935467 title="New Book Title" authors:='["Author One", "Author Two"]' location="Shelf A"
```

### Update an Existing Book

Update an existing book by its ISBN.

```sh
http PUT http://localhost:8080/api/books/9780060935467 isbn=9780060935467 title="Updated Book Title" authors:='["Updated Author One", "Updated Author Two"]' location="Updated Shelf"
```

### Delete a Book by ISBN

Delete a book by its ISBN.

```sh
http DELETE http://localhost:8080/api/books/9780060935467
```

## Example JSON Payloads

### Create a New Book

```json
{
  "isbn": "9780060935467",
  "title": "New Book Title",
  "authors": ["Author One", "Author Two"],
  "location": "Shelf A"
}
```

### Update an Existing Book

```json
{
  "isbn": "9780060935467",
  "title": "Updated Book Title",
  "authors": ["Updated Author One", "Updated Author Two"],
  "location": "Updated Shelf"
}
```

## Running the Application

1. Ensure your Spring Boot application is running.
2. Use the `httpie` commands provided above to interact with the API.

## Notes

- Replace `http://localhost:8080` with the actual URL of your running application if it's different.
- Ensure that the ISBN in the URL and the request body match for update and delete operations.

This `README.md` file provides a quick reference for users to interact with the Book API using `httpie` commands. Make sure to place this file in the `book` directory of your project.