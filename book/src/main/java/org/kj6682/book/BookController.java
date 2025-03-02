package org.kj6682.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.server.ServerErrorException;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books.html")
    public String all(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping(value = "/books.html", params = "isbn")
    public String get(@RequestParam("isbn") String isbn, Model model) {
        bookService.findByIsbn(isbn).ifPresent(book -> {
            model.addAttribute("book", book);
            model.addAttribute("authors", String.join(", ", book.getAuthors()));
        });
        return "books/details";
    }

    @PostMapping("/books/update")
    public String update(@RequestParam("isbn") String isbn,
                        @RequestParam("title") String title,
                        @RequestParam("authors") String authors,
                        @RequestParam("location") String location,
                        RedirectAttributes redirectAttributes) {
        try {
            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException("ISBN cannot be empty");
            }
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (authors == null || authors.trim().isEmpty()) {
                throw new IllegalArgumentException("Authors cannot be empty");
            }
            if (location == null || location.trim().isEmpty()) {
                throw new IllegalArgumentException("Location cannot be empty");
            }

            List<String> authorList = Arrays.asList(authors.split(",\\s*"));
            if (authorList.isEmpty()) {
                throw new IllegalArgumentException("At least one author must be specified");
            }

            Book updatedBook = new Book(isbn, title, authorList, location);
            Book savedBook = bookService.updateBook(updatedBook);
            if (savedBook != null) {
                redirectAttributes.addFlashAttribute("message", "Book updated successfully");
            } else {
                throw new BookNotFoundException("Failed to update book with ISBN: " + isbn);
            }
        } catch (BookNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Book not found with ISBN: " + isbn);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage() != null ? e.getMessage() : "An unexpected error occurred while updating the book");
        }
        return "redirect:/books.html?isbn=" + isbn;
    }

    @GetMapping("/books/500")
    public void error() {
        var cause = new NullPointerException("Dummy Exception");
        throw new ServerErrorException(cause.getMessage(), cause);
    }
}
