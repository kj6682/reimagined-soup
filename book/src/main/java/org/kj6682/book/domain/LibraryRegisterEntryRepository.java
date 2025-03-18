package org.kj6682.book.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraryRegisterEntryRepository extends CrudRepository<LibraryRegisterEntry, Long> {
}

