package org.kj6682.book.domain;

import org.kj6682.book.BookApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksAuthorsReporitory extends CrudRepository<BooksAuthors, Long> {
}

