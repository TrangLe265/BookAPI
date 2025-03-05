# Book Database API Documentation

This API allows you to manage a collection of books in a database. You can perform CRUD (Create, Read, Update, Delete) operations on books, as well as manage book categories.

## Base URL
http://localhost:8080/api

## Endpoints

### 1. Get All Books: `GET /books`
Fetches a list of all books in the database.

**Example Request:** http://localhost:8080/api/books

**Example Response:**
```json
[
  {
    "id": 1,
    "isbn": "978-0-12-345678-9",
    "title": "1984",
    "author": "George Orwell",
    "publicationYear": 1949,
    "price": 15.99,
    "category": {
      "id": 1,
      "name": "Dystopian"
    }
  },
  {
    "id": 2,
    "isbn": "978-1-234567-89-0",
    "title": "To Kill a Mockingbird",
    "author": "Harper Lee",
    "publicationYear": 1960,
    "price": 10.99,
    "category": {
      "id": 2,
      "name": "Classic"
    }
  }
]
```


### 2a. Get Book by Title: `GET /books/title/{title}`
Fetches a book by its title.

**Path Parameter:** `title` - The title of the book you want to fetch, connect each word by `%20`

**Example Request:** http://localhost:8080/api/books/title/1984

**Example Response:**
```json
{
    "id": 1,
    "isbn": "978-0-12-345678-9",
    "title": "1984",
    "author": "George Orwell",
    "publicationYear": 1949,
    "price": 15.99,
    "category": {
        "id": 1,
        "name": "Dystopian"
    }
}
```
#### 2b. Get Book by Publicaion Year: `GET /books/publicationYear/{publicationYear}`
Fetches books by a given publication year.

**Path Parameter:** `publicationYear` 

**Example Request:** http://localhost:8080/api/books/publicationYear/1984

#### 2c. Get Book by Authur: `GET /books/author/{author}`
Fetches books by a given author.

**Path Parameter:** `author` 

**Example Request:** http://localhost:8080/api/books/author/Jane%20Austen

#### 2d. Get Book by category: `GET /books/cat/{categoryName}`
Fetches books by a given category.

**Path Parameter:** `categoryName` 

**Example Request:** http://localhost:8080/api/books/cat/Fiction

### 3. Add a New Book: `POST /books`
Adds a new book to the database.

**Request Body Example:**
```json
{
    "isbn": "978-3-16-148410-0",
    "title": "Twilight",
    "author": "Stephen Meyer",
    "publicationYear": 2000,
    "price": 30.0,
    "categoryId": 1
}
```

**Example Request:** POST http://localhost:8080/api/books

### 4. Update Book Information: `PUT /books/{id}`
Updates the information of an existing book.

**Path Parameter:** `id` - The ID of the book you want to update.

**Request Body Example:**
```json
{
    "isbn": "978-3-16-148410-0",
    "title": "Twilight - Updated Edition",
    "author": "Stephen Meyer",
    "publicationYear": 2001,
    "price": 35.0,
    "categoryId": 2
}
```

**Example Request:** PUT http://localhost:8080/api/books/3

### 5. Delete a Book: `DELETE /books/{id}`
Deletes a book from the database.

**Path Parameter:** `id` - The ID of the book you want to delete.

**Example Request:** DELETE http://localhost:8080/api/books/3

**Example Response:**
```json
{
    "message": "Book with ID 3 has been deleted."
}
```

