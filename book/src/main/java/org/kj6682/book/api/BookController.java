package org.kj6682.book.api;

import org.kj6682.book.BookService;
import org.kj6682.book.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> all() {
        return bookService.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        return ResponseEntity.of(bookService.findByIsbn(isbn));
    }


    @GetMapping("/byLocation")
    public ResponseEntity<List<Book>> getBooksByLocation(@RequestParam("location") String location) {
        List<Book> books = bookService.findByLocation(location);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        var created = bookService.createBook(book);
        var newBookUri = uriComponentsBuilder.path("/api/books/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(newBookUri).body(created);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> update(@PathVariable("isbn") String isbn, @RequestBody Book book) {
        return ResponseEntity.of(bookService.updateBook(isbn, book));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable("isbn") String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}