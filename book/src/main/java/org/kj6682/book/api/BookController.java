package org.kj6682.book.api;

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
    public Iterable<BookDto> all() {
        return bookService.getAllBooks();
    }


    @GetMapping("/isbn/{isbn}")
    public Iterable<Book>  get(@PathVariable("isbn") String isbn) {
        return bookService.findByIsbn(isbn);
    }


    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        var created = bookService.createBook(book);
        var newBookUri = uriComponentsBuilder.path("/api/books/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(newBookUri).body(created);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
}