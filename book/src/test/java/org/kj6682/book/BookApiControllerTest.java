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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookApiController.class)
public class BookApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(bookService.findAll())
                .thenReturn(List.of(new Book("1234", "Uno", List.of("auth 01", "auth 02")),
                        new Book("5678", "Due", List.of("auth 03", "auth 04"))));
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].isbn", Matchers.containsInAnyOrder("1234", "5678")))
                .andExpect(jsonPath("$[*].title", Matchers.containsInAnyOrder("Uno", "Due")));
    }

    //@Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/123"))
                .andExpect(status().isNotFound());
    }

    //@Test
    public void shouldReturnBookWhenFound() throws Exception {
        when(bookService.find(anyString())).thenReturn(Optional.of(new Book("123", "La coscienza di Zeno", List.of("Italo Svevo"))));

        mockMvc.perform(get("/api/books/122"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", Matchers.equalTo("123")))
                .andExpect(jsonPath("$.title", Matchers.equalTo("La coscienza di Zeno")));
    }

    public void shouldAddBook() throws Exception {
        when(bookService.create(any(Book.class))).thenReturn(new Book("123456789", "Test Book Stored", List.of("T. Author")));
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"isbn\" : \"123456789\", \"title\" : \"Test Book\", \"authors\" : [\"T. Author\"]}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/books/123456789"));

    }


}//:)
