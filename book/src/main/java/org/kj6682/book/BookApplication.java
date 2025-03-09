package org.kj6682.book;

import jakarta.transaction.Transactional;
import org.kj6682.book.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Set;
//aN+HBf9TB9miO6aTcwa98b.0.4
//aN+HBf9TB9miO6aTcwa98b.0.7

@SpringBootApplication
public class BookApplication {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BooksAuthorsReporitory booksAuthorsReporitory;
    private final BookRepository bookRepository;

    public BookApplication(BookService bookService, AuthorRepository authorRepository, BooksAuthorsReporitory booksAuthorsReporitory, BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.booksAuthorsReporitory = booksAuthorsReporitory;
        this.bookRepository = bookRepository;
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

        Book book1 = new Book("9780061120084", "To Kill a Mockingbird", "ST01.01");
        Book book2 = new Book("9780451524935", "1984", "ST01.02");
        Book book3 = new Book("9780618260300", "The Hobbit", "ST01.03");
        Book book4 = new Book("9780140449136", "The Lord of the Rings", "ST01.04");
        Book book5 = new Book("9780060935467", "Der Prozess", "ST01.05");

        BooksAuthors booksAuthors1 = new BooksAuthors(book1, author1);
        BooksAuthors booksAuthors2 = new BooksAuthors(book2, author2);
        BooksAuthors booksAuthors3 = new BooksAuthors(book3, author3);
        BooksAuthors booksAuthors4 = new BooksAuthors(book4, author3);
        BooksAuthors booksAuthors5 = new BooksAuthors(book5, author4);

        //bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        //booksAuthorsReporitory.saveAll(List.of(booksAuthors1, booksAuthors2, booksAuthors3, booksAuthors4, booksAuthors5));
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);

        book1.setBooksAuthors(Set.of(booksAuthors1));
        author1.setBooksAuthors(Set.of(booksAuthors1));

        book2.setBooksAuthors(Set.of(booksAuthors2));
        author2.setBooksAuthors(Set.of(booksAuthors2));

        book3.setBooksAuthors(Set.of(booksAuthors3));
        book4.setBooksAuthors(Set.of(booksAuthors4));
        author3.setBooksAuthors(Set.of(booksAuthors3, booksAuthors4));

        author4.setBooksAuthors(Set.of(booksAuthors5));
        book5.setBooksAuthors(Set.of(booksAuthors5));

        authorRepository.saveAll(List.of(author1, author2, author3, author4));

    }


}
