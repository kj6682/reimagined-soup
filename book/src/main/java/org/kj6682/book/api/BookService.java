package org.kj6682.book.api;

import org.kj6682.book.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BooksAuthorsRepository booksAuthorsRepository;

    public List<Book> fetchAllBooks() {
        return (List<Book>) bookRepository.fetchAllBooks();
    }

    public List<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByLocation(String location) {
        return bookRepository.findByLocation(location);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setIsbn(book.getIsbn());
            updatedBook.setTitle(book.getTitle());
            updatedBook.setLocation(book.getLocation());
            // Update other fields as needed
            return bookRepository.save(updatedBook);
        } else {
            throw new RuntimeException("Book not found with id: " + book.getId());
        }
    }

    @jakarta.transaction.Transactional
    public void initializeDatabase() {
        Author author1 = new Author("Harper Lee");
        Author author2 = new Author("George Orwell");
        Author author3 = new Author("J.R.R. Tolkien");
        Author author4 = new Author("Franz Kafka");
        Author author5 = new Author("Christopher Brousseau");
        Author author6 = new Author("Matthew Sharp");


        authorRepository.saveAll(List.of(author1, author2, author3, author4, author5, author6));

        Book book1 = new Book("9780061120084", "To Kill a Mockingbird", "ST01.01");
        Book book2 = new Book("9780451524935", "1984", "ST01.02");
        Book book3 = new Book("9780618260300", "The Hobbit", "ST01.03");
        Book book4 = new Book("9780140449136", "The Lord of the Rings", "ST01.04");
        Book book5 = new Book("9780060935467", "Der Prozess", "ST01.05");
        Book book6 = new Book("9780060935467", "LLMs in Production", "ST01.06");
        bookRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6));

        BooksAuthors booksAuthors1 = new BooksAuthors(book1, author1);
        BooksAuthors booksAuthors2 = new BooksAuthors(book2, author2);
        BooksAuthors booksAuthors3 = new BooksAuthors(book3, author3);
        BooksAuthors booksAuthors4 = new BooksAuthors(book4, author3);
        BooksAuthors booksAuthors5 = new BooksAuthors(book5, author4);
        BooksAuthors booksAuthors6 = new BooksAuthors(book6, author5);
        BooksAuthors booksAuthors7 = new BooksAuthors(book6, author6);

        booksAuthorsRepository.saveAll(List.of(booksAuthors1, booksAuthors2, booksAuthors3, booksAuthors4, booksAuthors5, booksAuthors6, booksAuthors7));
    }
}