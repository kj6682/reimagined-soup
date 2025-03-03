package org.kj6682.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
    
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteAll() {
        bookRepository.deleteAll();
    }

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