package org.kj6682.book.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BooksAuthors> booksAuthors;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BooksAuthors> getBooksAuthors() {
        return booksAuthors;
    }
    public void setBooksAuthors(Set<BooksAuthors> booksAuthors) {
        this.booksAuthors = booksAuthors;
    }
}
