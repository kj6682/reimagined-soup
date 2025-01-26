package org.kj6682.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> find(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Transactional
    public void initializeDatabase() {
        // Insert new records
        bookRepository.saveAll(Arrays.asList(
                new Book("9780061120084", "To Kill a Mockingbird", Arrays.asList("Harper Lee")),
                new Book("9780451524935", "1984", Arrays.asList("George Orwell")),
                new Book("9780618260300", "The Hobbit", Arrays.asList("J.R.R. Tolkien")),
                new Book("9780140449136", "The Lord of the Rings", Arrays.asList("J.R.R. Tolkien")),
                new Book("9780060935467", "To Kill a Mockingbird", Arrays.asList("Harper Lee", "Another Author"))
        ));
    }


    @Transactional
    @Query("DELETE FROM Book b")
    void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    ;
}