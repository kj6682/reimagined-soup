package org.kj6682.book;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerErrorException;

import java.rmi.ServerException;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books.html")
    public String all(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping(value="/books.html", params = "isbn")
    public String get(@RequestParam("isbn") String isbn, Model model) {
        bookService.find(isbn).ifPresent(book->model.addAttribute("book", book));
        return "books/details";
    }
    
    @GetMapping("/books/500")
    public void error(){
        var cause = new NullPointerException("Dummy Exception");
        throw new ServerErrorException( cause.getMessage(), cause);
    }

}//:)
