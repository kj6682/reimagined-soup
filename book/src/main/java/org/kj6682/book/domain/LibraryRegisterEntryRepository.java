package org.kj6682.book.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRegisterEntryRepository extends CrudRepository<LibraryRegisterEntry, Long> {

    @Query(value = "SELECT b.id, b.isbn, b.title, STRING_AGG(a.name, ', ') AS authors, lre.location " +
            "FROM library_register_entry lre " +
            "JOIN book b ON lre.book_id = b.id " +
            "JOIN author a ON lre.author_id = a.id " +
            "GROUP BY b.id, b.isbn, b.title, lre.location", nativeQuery = true)
    List<Object[]> findAllBookDetails();

    @Query(value = "SELECT b.id, b.isbn, b.title, STRING_AGG(a.name, ', ') AS authors, lre.location " +
            "FROM library_register_entry lre " +
            "JOIN book b ON lre.book_id = b.id " +
            "JOIN author a ON lre.author_id = a.id " +
            "WHERE b.id = :bookId " +
            "GROUP BY b.id, b.isbn, b.title, lre.location", nativeQuery = true)
    List<Object[]> findBookDetailsByBookId(@Param("bookId") Long bookId);
}


