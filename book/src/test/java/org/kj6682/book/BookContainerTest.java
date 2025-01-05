package org.kj6682.book;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.http.ContentType;

@DataJpaTest
@Testcontainers
public class BookContainerTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAllInBatch();
    }

    @Test
    public void shouldGetAllBooks() {

        List<Book> books = List.of(new Book("Title 1", "Author 1", "ISBN 1"),
                new Book("Title 2", "Author 2", "ISBN 2"));

        bookRepository.saveAll(books);

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/customers")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }
}
