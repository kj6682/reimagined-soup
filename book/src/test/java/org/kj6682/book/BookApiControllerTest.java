package org.kj6682.book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/* The true problem of this test to be considered by the surefire pluging
 * is that vscode has some proplems in fixing the right imports.
 * I had to clean the imports and copy them from a working WebMvcTest unit test
 * 
 * This idiocy waisted me a lot of time.
 * 
 * use this command line to test this unit only"
 * 
 * ./mvnw clean test -Dtest="BookControllerTest"
 * 
 */

@WebMvcTest(BookApiController.class)
public class BookApiControllerTest {
   private static final Logger logger = LoggerFactory.getLogger(BookApiControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final Book book1 = new Book("123", "Spring 5 Recipies", List.of("Marten Deinum", "Josh Long"));
    private final Book book2 = new Book("321", "Pro Spring Mvc", List.of("Marten Deinum", "Colin Yates"));
    
    @Test
    public void shouldReturnListOfBooks() throws Exception {

        when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].isbn", Matchers.containsInAnyOrder("123", "321")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].title",
                        Matchers.containsInAnyOrder("Spring 5 Recipies", "Pro Spring Mvc")));

    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception {
        when(bookService.find(anyString())).thenReturn(Optional.of(book1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/123"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.equalTo(book1.isbn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(book1.title())));
    }

    @Test
    public void shouldAddBook() throws Exception{
        when(bookService.create(any(Book.class)))
        .thenReturn(book2);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"isbn\" : \"321\", \"title\" : \"Pro Spring Mvc\", \"authors\" : [\"Marten Deinum\", \"Colin Yates\"]}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "http://localhost/books/321"));
    }
}
