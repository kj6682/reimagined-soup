package org.kj6682.book;

import org.kj6682.book.domain.AuthorRepository;
import org.kj6682.book.domain.Book;
import org.kj6682.book.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LocaleResolver localeResolver;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, LocaleResolver localeResolver) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.localeResolver = localeResolver;
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
    
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByLocation(String location) {
        return bookRepository.findByLocation(location);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Transactional
    public void saveAll(List<Book> books){
        bookRepository.saveAll(books);
    }

    @Transactional
    public Optional<Book> updateBook(String isbn, Book updatedBook) {
        return bookRepository.findByIsbn(isbn).map(book -> {
            book.setIsbn(updatedBook.getIsbn());
            book.setTitle(updatedBook.getTitle());
            book.setAuthors(updatedBook.getAuthors());
            book.setLocation(updatedBook.getLocation());
            return bookRepository.save(book);
        });
    }

    @Transactional
    public void deleteBook(String isbn) {
        bookRepository.findByIsbn(isbn).ifPresent(bookRepository::delete);
    }

}