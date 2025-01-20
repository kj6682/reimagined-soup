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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnListOfBooks() throws Exception{
        when(bookService.findAll())
                .thenReturn(List.of(new Book("1234", "Uno", List.of("auth 01", "auth 02")),
                        new Book("5678", "Due", List.of("auth 03", "auth 04"))));
        mockMvc.perform(get("/books.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attribute("books", Matchers.hasSize(2)));
    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception{
        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/books.html").param("isbn", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attributeDoesNotExist("books"));
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception{
        Book book = new Book("123", "La coscienza di Zeno", List.of("Italo Svevo"));
        when(bookService.find(anyString())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/books.html").param("isbn", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attribute("book", Matchers.is(book)));
    }

    public void shouldAddBook() throws Exception{
        when(bookService.create(any(Book.class))).thenReturn(new Book("123456789", "Test Book Stored", List.of("T. Author")));
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"isbn\" : \"123456789\", \"title\" : \"Test Book\", \"authors\" : [\"T. Author\"]}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/books/123456789"));

    }


}//:)
