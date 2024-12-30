package org.kj6682.book;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final Book book1 = new Book("123", "Spring 6 Recipies", List.of("Marten Deinum", "Josh Long"));
    private final Book book2 = new Book("321", "Pro Spring Mvc", List.of("Marten Deinum", "Colin Yates"));

    @Test
    public void shouldReturnListOfBooks() throws Exception {

        Mockito.when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(MockMvcRequestBuilders.get("/books.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attribute("books", Matchers.hasSize(2)));
    }

    @Test
    public void shouldReturnNoBooksWhenNotFound() throws Exception {

        Mockito.when(bookService.find(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/books.html").param("isbn", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attributeDoesNotExist("book"));
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception {

        Mockito.when(bookService.find(ArgumentMatchers.anyString())).thenReturn(Optional.of(book1));

        mockMvc.perform(MockMvcRequestBuilders.get("/books.html").param("isbn", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attribute("book", Matchers.is(book1)));
    }
}// :)
