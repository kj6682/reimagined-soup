package org.kj6682.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Arrays;
import java.util.Locale;


@SpringBootApplication
public class BookApplication implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
    private final BookService bookService;

    public BookApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    @Profile("init-postgres")
    public CommandLineRunner demo(BookService bookService) {
        return (args) -> {
            bookService.deleteAll();
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

    private void initializeDatabase() {
        bookService.saveAll(Arrays.asList(
                new Book("9780061120084", "To Kill a Mockingbird", Arrays.asList("Harper Lee"), "ST01.01"),
                new Book("9780451524935", "1984", Arrays.asList("George Orwell"), "ST01.02"),
                new Book("9780618260300", "The Hobbit", Arrays.asList("J.R.R. Tolkien"), "ST01.03"),
                new Book("9780140449136", "The Lord of the Rings", Arrays.asList("J.R.R. Tolkien"), "ST01.04"),
                new Book("9780060935467", "To Kill a Mockingbird", Arrays.asList("Harper Lee", "Another Author"), "ST01.05")
        ));
    }

    @Bean
    public CustomizedErrorAttributes errorAttributes() {
        return new CustomizedErrorAttributes();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ITALIAN);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
