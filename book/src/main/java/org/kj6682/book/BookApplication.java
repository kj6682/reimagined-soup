package org.kj6682.book;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class BookApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public ApplicationRunner bookInitializer(BookService bookService) {
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

    @Bean
    public CustomizedErrorAttributes errorAttributes(){
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
