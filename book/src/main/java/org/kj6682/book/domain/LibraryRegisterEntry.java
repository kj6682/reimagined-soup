package org.kj6682.book.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class LibraryRegisterEntry {

    public static final String NO_LOCATION_IN_LIBRARY = "NOWHERE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public String location;

    public LibraryRegisterEntry() {}

    public LibraryRegisterEntry(Book book, Author author, String location) {
        this.book = book;
        this.author = author;
        this.location = location;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryRegisterEntry that = (LibraryRegisterEntry) o;
        return Objects.equals(book, that.book) && Objects.equals(author, that.author);
    }
    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
