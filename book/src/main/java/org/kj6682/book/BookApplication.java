package org.kj6682.book;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @PostConstruct
    public void sayHello() {
        System.out.println("Just to remember the PostConstruct annotation:\n>>>VigilantMemory is up and running!");
    }


    public ApplicationRunner lister(JdbcTemplate jdbc) {
        return (args) -> {
            jdbc.query("select * from pg_catalog.pg_tables", rs -> {
                System.out.printf("Table: %s.%s%n", rs.getString(1), rs.getString(2));
            });
        };
    }

    @Bean
    public ApplicationRunner booksInitialiser(BookService bookService) {
        return args -> {
            bookService.create(
                    new Book("9780061120084", "To Kill a Mockingbird",
                            List.of("Harper Lee")));
            bookService.create(
                    new Book("9780451524935", "1984",
                            List.of("George Orwell")));
            bookService.create(
                    new Book("9780618260300", "The Hobbit",
                            List.of("J.R.R. Tolkien")));

        };
    }

    

}// :)
