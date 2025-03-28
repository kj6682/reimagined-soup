package org.kj6682.book.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();

    @Query("SELECT a FROM Author a WHERE a.name LIKE %:name%")
    List<Author> findByName(@Param("name") String name);
}
