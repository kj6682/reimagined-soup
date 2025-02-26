package org.kj6682.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@SpringBootApplication
public class BookApplication implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    @Profile("init-postgres")
    public CommandLineRunner demo(BookService bookService) {
        log.info("Initializing demo...");
        return (args) -> {
            // Delete if any record exists
            bookService.deleteAllBooks();
            // Save a few books with a list of authors
            bookService.initializeDatabase();
            showInitialRecords(bookService);
        };
    }

    private static void showInitialRecords(BookService bookService) {
        // Fetch all books
        log.info("Books found with findAll():");
        log.info("-------------------------------");
        for (Book book : bookService.findAll()) {
            log.info(book.toString());
        }
        log.info("");

        // Fetch an individual book by ID
        Book book = bookService.find("9780060935467").get();
        log.info("Book found with findById(9780060935467):");
        log.info("--------------------------------");
        log.info(book.toString());
        log.info("");
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
