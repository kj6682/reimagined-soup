package org.kj6682.book.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class BooksAuthors {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public BooksAuthors() {}

    public BooksAuthors(Book book, Author author) {
        this.book = book;
        this.author = author;
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
    public String toString() {
        return "BooksAuthors{" +
                "id=" + id +
                ", book=" + book +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksAuthors that = (BooksAuthors) o;
        return Objects.equals(book, that.book) && Objects.equals(author, that.author);
    }
    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}
