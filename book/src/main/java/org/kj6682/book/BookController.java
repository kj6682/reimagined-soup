package org.kj6682.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
        return ResponseEntity.of(bookService.find(isbn));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        var created = bookService.create(book);
        var newBookUri = uriComponentsBuilder.path("/api/books/{isbn}").build(created.isbn());
        return ResponseEntity.created(newBookUri).body(created);
    }
}