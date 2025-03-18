package org.kj6682.book.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public Iterable<AuthorCompleteDto> all() {
        return authorService.getAllAuthors();
    }
}
