package org.kj6682.book.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/api/book-details")
    public List<BookDetailsDto> getBookDetails(@RequestParam Long bookId) {
        return libraryService.getBookDetailsByBookId(bookId);
    }
}