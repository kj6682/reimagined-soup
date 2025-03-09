package org.kj6682.book.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Query("select c from Book c where c.location = ?1")
    List<Book> findByLocation(String location);

    @Query("SELECT b FROM Book b " +
            "JOIN b.booksAuthors ba " +
            "JOIN ba.author a " +
            "WHERE b.id = :bookId")
    Book findBookWithAuthors(@Param("bookId") Long bookId);

    @Query("SELECT b FROM Book b " +
            "JOIN b.booksAuthors ba " +
            "JOIN ba.author a " +
            "WHERE b.isbn = :isbn")
    Optional<Book> findBookWithAuthorsByIsbn(@Param("isbn") String isbn);

}
