package org.kj6682.book;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(bookService.findAll())
                .thenReturn(
                        List.of(
                                new Book("1234", "Uno", List.of("auth 01", "auth 02"), "Shelf A"),
                                new Book("5678", "Due", List.of("auth 03", "auth 04"), "Shelf B")));
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].isbn", Matchers.containsInAnyOrder("1234", "5678")))
                .andExpect(jsonPath("$[*].title", Matchers.containsInAnyOrder("Uno", "Due")));
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception {
        String isbn = "1234";
        when(bookService.findByIsbn(isbn))
                .thenReturn(Optional.of(new Book(isbn, "Uno", List.of("auth 01", "auth 02"), "Shelf A")));
        mockMvc.perform(get("/api/books/{isbn}", isbn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(isbn))
                .andExpect(jsonPath("$.title").value("Uno"));
    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        String isbn = "9999";
        when(bookService.findByIsbn(isbn)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/{isbn}", isbn))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddBook() throws Exception {
        Book newBook = new Book("1234", "Uno", List.of("auth 01", "auth 02"), "Shelf A");
        when(bookService.createBook(any(Book.class))).thenReturn(newBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"1234\",\"title\":\"Uno\",\"authors\":[\"auth 01\",\"auth 02\"],\"location\":\"Shelf A\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/books/1234"))
                .andExpect(jsonPath("$.isbn").value("1234"))
                .andExpect(jsonPath("$.title").value("Uno"));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        String isbn = "1234";
        Book updatedBook = new Book(isbn, "Updated Title", List.of("Updated Author"), "Updated Location");
        when(bookService.updateBook(eq(isbn), any(Book.class))).thenReturn(Optional.of(updatedBook));

        mockMvc.perform(put("/api/books/{isbn}", isbn)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"1234\",\"title\":\"Updated Title\",\"authors\":[\"Updated Author\"],\"location\":\"Updated Location\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(isbn))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.authors", Matchers.containsInAnyOrder("Updated Author")))
                .andExpect(jsonPath("$.location").value("Updated Location"));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        String isbn = "1234";
        doNothing().when(bookService).deleteBook(isbn);

        mockMvc.perform(delete("/api/books/{isbn}", isbn))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteBook(isbn);
    }
}
