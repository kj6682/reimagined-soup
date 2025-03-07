package org.kj6682.book;

import jakarta.transaction.Transactional;
import org.kj6682.book.domain.Author;
import org.kj6682.book.domain.AuthorRepository;
import org.kj6682.book.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class BookApplication {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    public BookApplication(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    @Profile("init-postgres")
    @Transactional
    public CommandLineRunner demo(BookService bookService) {
        return (args) -> {
            //bookService.deleteAll();
            // Save a few books with a list of authors
            initializeDatabase();
            // Fetch all books
            log.info("Books found with findAll():");
            log.info("-------------------------------");
            for (Book book : bookService.findAll()) {
                log.info(book.toString());
            }
            log.info("");

            // Fetch an individual book by ID
            Book book = bookService.findByIsbn("9780060935467").get();
            log.info("Book found with findById(9780060935467):");
            log.info("--------------------------------");
            log.info(book.toString());
            log.info("");
        };
    }

    @Transactional
    public void initializeDatabase() {
        Author author1 = new Author("Harper Lee");
        Author author2 = new Author("George Orwell");
        Author author3 = new Author("J.R.R. Tolkien");
        Author author4 = new Author("Franz Kafka");

        authorRepository.saveAll(List.of(author1, author2, author3, author4));

        bookService.saveAll(List.of(
                new Book("9780061120084", "To Kill a Mockingbird", Set.of(author1), "ST01.01"),
                new Book("9780451524935", "1984", Set.of(author2), "ST01.02"),
                new Book("9780618260300", "The Hobbit", Set.of(author3), "ST01.03"),
                new Book("9780140449136", "The Lord of the Rings", Set.of(author3), "ST01.04"),
                new Book("9780060935467", "Great Anthology of Short Stories", Set.of(author4, author3, author2, author1), "ST01.05")
        ));
    }



}
