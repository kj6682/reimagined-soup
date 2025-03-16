package org.kj6682.book.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b")
    List<Book> fetchAllBooks();

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.isbn LIKE %:isbn%")
    List<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b WHERE b.location LIKE %:location%")
    List<Book> findByLocation(@Param("location") String location);

    @Query("SELECT b FROM Book b " +
            "JOIN b.booksAuthors ba " +
            "JOIN ba.author a " +
            "WHERE b.id = :bookId")
    Book findBookWithAuthors(@Param("bookId") Long bookId);

}
