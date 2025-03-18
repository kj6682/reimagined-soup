package org.kj6682.book.domain;

import org.kj6682.book.api.BookDetailsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRegisterEntryRepository extends CrudRepository<LibraryRegisterEntry, Long> {

    @Query("SELECT new org.kj6682.book.api.BookDetailsDto(b.title, a.name, lre.location) " +
            "FROM LibraryRegisterEntry lre " +
            "JOIN lre.book b " +
            "JOIN lre.author a " +
            "WHERE lre.book.id = :bookId")
    List<BookDetailsDto> findBookDetailsByBookId(@Param("bookId") Long bookId);
}
