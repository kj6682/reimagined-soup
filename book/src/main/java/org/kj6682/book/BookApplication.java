package org.kj6682.book;

import org.kj6682.book.api.BookService;
import org.kj6682.book.api.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
//aN+HBf9TB9miO6aTcwa98b.0.4
//aN+HBf9TB9miO6aTcwa98b.0.7

@SpringBootApplication
public class BookApplication {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
    @Autowired
    private LibraryService libraryService;

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
            libraryService.initializeDatabase();
          /*  // Fetch all books
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

           */
        };
    }




}
