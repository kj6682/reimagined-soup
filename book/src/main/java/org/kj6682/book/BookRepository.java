package org.kj6682.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book, Long> {

    Book findById(long id);
    Optional<Book> findByIsbn(String isbn);

}
