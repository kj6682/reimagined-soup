package org.kj6682.book;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Book updateBook(Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findByIsbn(updatedBook.getIsbn());
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthors(updatedBook.getAuthors());
            existingBook.setLocation(updatedBook.getLocation());
            return bookRepository.save(existingBook);
        } else {
            throw new IllegalArgumentException("Book with ISBN " + updatedBook.getIsbn() + " not found");
        }
    }

    @Transactional
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public void saveAll(List<Book> books){
        bookRepository.saveAll(books);
    }
}